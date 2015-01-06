package com.paeez.core.services.exceptions;

/**
 * Created by Shrikant on 1/4/15.
 */
public class InputIdNullOrEmptyException extends RuntimeException {
    public InputIdNullOrEmptyException() {
    }

    public InputIdNullOrEmptyException(String message) {
        super(message);
    }

    public InputIdNullOrEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public InputIdNullOrEmptyException(Throwable cause) {
        super(cause);
    }

    public InputIdNullOrEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
