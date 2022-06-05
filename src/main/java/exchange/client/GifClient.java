package exchange.client;

import exchange.dto.request.GifRequestDTO;
import exchange.dto.response.GifDTO;
import feign.QueryMap;
import feign.RequestLine;

public interface GifClient {
    @RequestLine("GET /random")
    GifDTO getRandomGif(@QueryMap GifRequestDTO query);
}
