package com.paeez.core.services.exceptions;

/**
 * Created by Shrikant on 1/4/15.
 */
public class GenericBetNotFoundException extends RuntimeException {
    public GenericBetNotFoundException() {
    }

    public GenericBetNotFoundException(String message) {
        super(message);
    }

    public GenericBetNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public GenericBetNotFoundException(Throwable cause) {
        super(cause);
    }

    public GenericBetNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
