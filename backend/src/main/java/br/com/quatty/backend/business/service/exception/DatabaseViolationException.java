package br.com.quatty.backend.business.service.exception;

public class DatabaseViolationException extends RuntimeException{

    public DatabaseViolationException(String message) {
        super(message);
    }

    public DatabaseViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
