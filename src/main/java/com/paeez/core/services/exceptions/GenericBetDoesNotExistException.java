package com.paeez.core.services.exceptions;

/**
 * Created by Shrikant on 1/5/15.
 */
public class GenericBetDoesNotExistException extends RuntimeException {
    public GenericBetDoesNotExistException() {
    }

    public GenericBetDoesNotExistException(String message) {
        super(message);
    }

    public GenericBetDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public GenericBetDoesNotExistException(Throwable cause) {
        super(cause);
    }

    public GenericBetDoesNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
