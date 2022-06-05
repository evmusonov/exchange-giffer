package exchange.dto.response;

public class GifDTO {
    private GifDataDTO data;

    public GifDTO () {}

    public GifDTO(GifDataDTO gifDataDTO) {
        this.data = gifDataDTO;
    }

    public void setData(GifDataDTO data) {
        this.data = data;
    }

    public GifDataDTO getData() {
        return this.data;
    }
}
