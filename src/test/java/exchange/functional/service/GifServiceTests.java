package exchange.functional.service;

import exchange.client.GifClient;
import exchange.dto.request.GifRequestDTO;
import exchange.dto.response.GifDTO;
import exchange.dto.response.GifDataDTO;
import exchange.service.GifService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class GifServiceTests {
    @Autowired
    private GifService gifService;
    @MockBean
    private GifClient gifClient;

    @Test
    void getGifDependsOnCurrencyRate() {
        // arrange
        String gifID = "random";

        // act
        Mockito
            .when(gifClient.getRandomGif(Mockito.any(GifRequestDTO.class)))
            .thenReturn(new GifDTO(new GifDataDTO(gifID)));
        String randomGifURL = gifService.getGifURLDependsOnCurrencyRate(
            60f,
            59f
        );

        // assert
        Mockito
            .verify(gifClient, Mockito.times(1))
            .getRandomGif(ArgumentMatchers.any(GifRequestDTO.class));
        Assertions.assertEquals(gifService.getCorrectURL(gifID), randomGifURL);
    }
}
