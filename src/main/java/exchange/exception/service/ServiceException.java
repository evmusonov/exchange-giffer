package exchange.exception.service;

public class ServiceException extends RuntimeException {
    public ServiceException(String message, Throwable e) {
        super(message, e);
    }
}
