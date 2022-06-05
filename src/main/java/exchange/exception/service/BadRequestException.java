package exchange.exception.service;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message, Throwable e) {
        super(message, e);
    }
}
