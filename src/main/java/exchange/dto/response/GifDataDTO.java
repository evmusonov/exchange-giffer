package exchange.dto.response;

public class GifDataDTO {
    private String id;

    public GifDataDTO() {}

    public GifDataDTO(String id) {
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}
