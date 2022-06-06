package exchange.dto.request;

import feign.Param;

public class GifRequestDTO {
    private String apiKey;
    private String tag;

    public GifRequestDTO(String apiKey, String tag) {
        this.apiKey = apiKey;
        this.tag = tag;
    }

    @Param("api_key")
    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
