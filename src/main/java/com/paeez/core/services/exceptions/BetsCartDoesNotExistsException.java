package com.paeez.core.services.exceptions;

/**
 * Created by Shrikant on 1/4/15.
 */
public class BetsCartDoesNotExistsException extends RuntimeException {
    public BetsCartDoesNotExistsException() {
    }

    public BetsCartDoesNotExistsException(String message) {
        super(message);
    }

    public BetsCartDoesNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public BetsCartDoesNotExistsException(Throwable cause) {
        super(cause);
    }

    public BetsCartDoesNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
