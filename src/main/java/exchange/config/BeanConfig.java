package exchange.config;

import exchange.ExchangeGifferApplication;
import exchange.client.ExchangeRatesClient;
import exchange.client.GifClient;
import exchange.dto.request.ExchangeRequestDTO;
import exchange.dto.request.GifRequestDTO;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.querymap.BeanQueryMapEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public ExchangeRatesClient exchangeRatesClient() {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .queryMapEncoder(new BeanQueryMapEncoder())
                .target(ExchangeRatesClient.class, ApplicationConfig.EXCHANGE_URL_ENTRYPOINT);
    }

    @Bean
    public GifClient gifClient() {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .queryMapEncoder(new BeanQueryMapEncoder())
                .target(GifClient.class, ApplicationConfig.GIF_URL_ENTRYPOINT);
    }

    @Bean
    public Logger logger() {
        return LogManager.getLogger(ExchangeGifferApplication.class);
    }

    @Bean
    public ExchangeRequestDTO exchangeRequestDTO() {
        return new ExchangeRequestDTO(
            ApplicationConfig.EXCHANGE_APP_ID,
            ApplicationConfig.BASE_CURRENCY,
            null,
            false,
            false
        );
    }

    @Bean
    public GifRequestDTO gifRequestDTO() {
        return new GifRequestDTO(
                ApplicationConfig.GIF_APP_ID,
                null
        );
    }
}
