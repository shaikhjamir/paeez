package com.paeez.core.services.exceptions;

/**
 * Created by Shrikant on 1/4/15.
 */
public class BetsCartIdDoesNotExistsException extends RuntimeException {
    public BetsCartIdDoesNotExistsException() {
    }

    public BetsCartIdDoesNotExistsException(String message) {
        super(message);
    }

    public BetsCartIdDoesNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public BetsCartIdDoesNotExistsException(Throwable cause) {
        super(cause);
    }

    public BetsCartIdDoesNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
