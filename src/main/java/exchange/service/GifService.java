package exchange.service;

import exchange.client.GifClient;
import exchange.dto.request.GifRequestDTO;
import exchange.dto.response.GifDTO;
import exchange.exception.service.*;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class GifService {
    @Autowired
    private GifClient gifClient;
    @Autowired
    private GifRequestDTO gifRequestDTO;

    public String getGifURLDependsOnCurrencyRate(
            Float currentCurrencyRate,
            Float anotherCurrencyRate
    ) {
        if (currentCurrencyRate.compareTo(anotherCurrencyRate) > 0) {
            gifRequestDTO.setTag("rich");
        } else {
            gifRequestDTO.setTag("broke");
        }

        try {
            GifDTO gifDTO = Optional.ofNullable(gifClient.getRandomGif(gifRequestDTO))
                .orElseThrow(() -> new NotFoundException("Gif изображение не найдено"));

            if (gifDTO.getData().getId() != null) {
                return getCorrectURL(gifDTO.getData().getId());
            } else {
                throw new NotFoundException("Gif изображение не найдено");
            }
        } catch (FeignException.TooManyRequests e) {
            throw new TooManyRequestsException("Превышен лимит запросов", e);
        } catch (FeignException.NotFound e) {
            throw new NotFoundException("Gif изображение не найдено", e);
        } catch (FeignException.Forbidden e) {
            throw new ForbiddenException("Доступ запрещен", e);
        } catch (FeignException.BadRequest e) {
            throw new BadRequestException("Неверные параметры запроса", e);
        } catch (Throwable e) {
            throw new ServiceException("Не удалось получить gif изображение", e);
        }
    }

    public String getCorrectURL(String gifID) {
        return String.format("https://i.giphy.com/media/%s/giphy.gif", gifID);
    }
}
