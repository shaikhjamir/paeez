package com.paeez.core.services.exceptions;

/**
 * Created by Shrikant on 1/4/15.
 */
public class UserIdDoesNotExistsException extends RuntimeException {
    public UserIdDoesNotExistsException() {
    }

    public UserIdDoesNotExistsException(String message) {
        super(message);
    }

    public UserIdDoesNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserIdDoesNotExistsException(Throwable cause) {
        super(cause);
    }

    public UserIdDoesNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
