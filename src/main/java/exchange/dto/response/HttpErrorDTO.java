package exchange.dto.response;

public class HttpErrorDTO {
    private String message;
    private int status;

    public HttpErrorDTO(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
