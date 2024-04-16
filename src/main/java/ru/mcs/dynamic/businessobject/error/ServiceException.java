package ru.mcs.dynamic.businessobject.error;

import org.springframework.http.HttpStatus;

public class ServiceException extends RuntimeException {
    private final Error error;

    public ServiceException(Error error, Object... args) {
        super(error.getMessage().formatted(args));
        this.error = error;
    }

    public ServiceException(Error error) {
        super(error.getMessage());
        this.error = error;
    }

    public ServiceException(HttpStatus status, String message) {
        super(message);
        this.error = new DefaultError(status, message);
    }

    public ServiceException(HttpStatus status, String message, Object... args) {
        this(status, message.formatted(args));
    }

    public ServiceException(String code, HttpStatus status, String message) {
        super(message);
        this.error = new DefaultError(code, status, message);
    }

    public ServiceException(String code, HttpStatus status, String message, Object... args) {
        this(code, status, message.formatted(args));
    }

    public String getCode() {
        return error.getCode();
    }

    public HttpStatus getHttpStatus() {
        return error.getHttpStatus();
    }

    private static class DefaultError implements Error {

        private final String code;
        private final HttpStatus status;
        private final String message;

        public DefaultError(String code, HttpStatus status, String message) {
            this.code = code;
            this.status = status;
            this.message = message;
        }

        public DefaultError(HttpStatus status, String message) {
            this("undefined-code", status, message);
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public HttpStatus getHttpStatus() {
            return this.status;
        }

        @Override
        public String getMessage() {
            return this.message;
        }
    }
}
