package exchange.exception.service;

public class TooManyRequestsException extends RuntimeException {
    public TooManyRequestsException(String message, Throwable e) {
        super(message, e);
    }
}
