package exchange.dto.request;

public class GifRequestDTO {
    private String api_key;
    private String tag;

    public GifRequestDTO(String apiKey, String tag) {
        this.api_key = apiKey;
        this.tag = tag;
    }

    public String getApiKey() {
        return api_key;
    }

    public void setApiKey(String apiKey) {
        this.api_key = apiKey;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
