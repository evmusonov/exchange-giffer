package exchange.exception.service;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message, Throwable e) {
        super(message, e);
    }
}
