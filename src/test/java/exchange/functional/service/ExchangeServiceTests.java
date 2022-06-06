package exchange.functional.service;

import exchange.client.ExchangeRatesClient;
import exchange.config.ApplicationConfig;
import exchange.dto.request.ExchangeRequestDTO;
import exchange.dto.response.CurrencyRatesDTO;
import exchange.service.ExchangeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.HashMap;

@SpringBootTest
public class ExchangeServiceTests {
    @Autowired
    private ExchangeService exchangeService;
    @MockBean
    private ExchangeRatesClient exchangeRatesClient;
    private final String currencyCode = "RUB";
    private final float expectedRate = 60;

    @Test
    void getCurrentCurrencyRate() {
        // act
        Mockito
            .when(exchangeRatesClient.getLatestRates(Mockito.any(ExchangeRequestDTO.class)))
            .thenReturn(new CurrencyRatesDTO(
                0L,
                ApplicationConfig.BASE_CURRENCY,
                new HashMap<>() {{
                    put(currencyCode, expectedRate);
                }}
            ));
        float currentRate = exchangeService.getCurrentCurrencyRate(currencyCode);

        // assert
        Mockito
            .verify(exchangeRatesClient, Mockito.times(1))
            .getLatestRates(ArgumentMatchers.any(ExchangeRequestDTO.class));
        Assertions.assertEquals(expectedRate, currentRate);
    }

    @Test
    void getCurrencyRateByDate() {
        // act
        Mockito
                .when(exchangeRatesClient.getRatesByDate(Mockito.anyString(), Mockito.any(ExchangeRequestDTO.class)))
                .thenReturn(new CurrencyRatesDTO(
                        0L,
                        ApplicationConfig.BASE_CURRENCY,
                        new HashMap<>() {{
                            put(currencyCode, expectedRate);
                        }}
                ));
        float prevRate = exchangeService.getCurrencyRateByDate(
                currencyCode,
                LocalDate.now().minusDays(1)
        );

        // assert
        Mockito
            .verify(exchangeRatesClient, Mockito.times(1))
            .getRatesByDate(ArgumentMatchers.anyString(), ArgumentMatchers.any(ExchangeRequestDTO.class));
        Assertions.assertEquals(expectedRate, prevRate);
    }
}
