package exchange.controller;

import exchange.exception.service.*;
import exchange.service.ExchangeService;
import exchange.service.GifService;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;

@RestController
public class ExchangeController {
    @Autowired
    private ExchangeService exchangeService;
    @Autowired
    private GifService gifService;
    @Autowired
    private Logger logger;

    @GetMapping(
        produces = MediaType.IMAGE_GIF_VALUE
    )
    public byte[] get(@RequestParam(name = "currency", defaultValue = "RUB") String currencyCode) {
        try {
            float currentCurrencyRate = exchangeService.getCurrentCurrencyRate(currencyCode);
            float previousCurrencyRate = exchangeService.
                    getCurrencyRateByDate(currencyCode, LocalDate.now().minusDays(1));

            logger.info(
                "Currency code: {}, current rate: {}, prev rate: {}",
                currencyCode,
                currentCurrencyRate,
                previousCurrencyRate
            );

            String gifURL = gifService.getGifURLDependsOnCurrencyRate(
                    currentCurrencyRate,
                    previousCurrencyRate
            );

            URL url = new URL(gifURL);
            try(InputStream is = url.openStream()) {
                return IOUtils.toByteArray(is);
            }
        } catch (TooManyRequestsException e) {
            throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS, e.getMessage(), e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (ForbiddenException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (IOException | ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
