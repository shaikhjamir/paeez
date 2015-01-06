package com.paeez.core.services.exceptions;

/**
 * Created by Shrikant on 1/4/15.
 */
public class BetsCartNotFoundException extends RuntimeException {
    public BetsCartNotFoundException() {
    }

    public BetsCartNotFoundException(String message) {
        super(message);
    }

    public BetsCartNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BetsCartNotFoundException(Throwable cause) {
        super(cause);
    }

    public BetsCartNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
