package exchange.client;

import exchange.dto.request.ExchangeRequestDTO;
import exchange.dto.response.CurrencyRatesDTO;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;

public interface ExchangeRatesClient {
    @RequestLine("GET /latest.json")
    CurrencyRatesDTO getLatestRates(@QueryMap ExchangeRequestDTO exchangeRequestDTO);

    @RequestLine("GET /historical/{date}.json")
    CurrencyRatesDTO getRatesByDate(
            @Param("date") String date,
            @QueryMap ExchangeRequestDTO exchangeRequestDTO
    );
}
