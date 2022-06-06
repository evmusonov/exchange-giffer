package exchange.service;

import exchange.client.ExchangeRatesClient;
import exchange.dto.request.ExchangeRequestDTO;
import exchange.dto.response.CurrencyRatesDTO;
import exchange.exception.service.*;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ExchangeService {
    @Autowired
    private ExchangeRatesClient exchangeRatesClient;
    @Autowired
    private ExchangeRequestDTO exchangeRequestDTO;

    public float getCurrentCurrencyRate(String currencyCode) {
        String formattedCurrencyCode = currencyCode.toUpperCase();
        exchangeRequestDTO.setSymbols(formattedCurrencyCode);

        try {
            CurrencyRatesDTO latestRatesDTO = Optional.ofNullable(
                exchangeRatesClient.getLatestRates(exchangeRequestDTO))
                .orElseThrow(() -> new NotFoundException("Валютная пара не найдена"));

            if (latestRatesDTO.getRates().containsKey(formattedCurrencyCode)) {
                return latestRatesDTO.getRates().get(formattedCurrencyCode);
            } else {
                throw new NotFoundException("Валютная пара не найдена");
            }
        } catch (FeignException.NotFound e) {
            throw new NotFoundException("Запрашиваемый ресурс не найден", e);
        } catch (FeignException.Forbidden | FeignException.TooManyRequests e) {
            throw new ForbiddenException("Доступ запрещен", e);
        } catch (FeignException.Unauthorized | FeignException.BadRequest e) {
            throw new BadRequestException("Неверные параметры запроса", e);
        } catch (Throwable e) {
            throw new ServiceException("Не удалось получить курс валюты", e);
        }
    }

    public float getCurrencyRateByDate(String currencyCode, LocalDate date) {
        String formattedCurrencyCode = currencyCode.toUpperCase();
        exchangeRequestDTO.setSymbols(formattedCurrencyCode);

        try {
            CurrencyRatesDTO latestRatesDTO = Optional.ofNullable(
                exchangeRatesClient.getRatesByDate(date.toString(), exchangeRequestDTO))
                .orElseThrow(() -> new NotFoundException("Валютная пара не найдена"));

            if (latestRatesDTO.getRates().containsKey(formattedCurrencyCode)) {
                return latestRatesDTO.getRates().get(formattedCurrencyCode);
            } else {
                throw new NotFoundException("Валютная пара не найдена");
            }
        } catch (FeignException.NotFound e) {
            throw new NotFoundException("Запрашиваемый ресурс не найден", e);
        } catch (FeignException.Forbidden | FeignException.TooManyRequests e) {
            throw new ForbiddenException("Доступ запрещен", e);
        } catch (FeignException.Unauthorized | FeignException.BadRequest e) {
            throw new BadRequestException("Неверные параметры запроса", e);
        } catch (Throwable e) {
            throw new ServiceException("Не удалось получить курс валюты", e);
        }
    }
}
