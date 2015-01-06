package com.paeez.core.services.exceptions;

/**
 * Created by Shrikant on 1/4/15.
 */
public class UserIdAlreadyExistsException extends RuntimeException {
    public UserIdAlreadyExistsException() {
    }

    public UserIdAlreadyExistsException(String message) {
        super(message);
    }

    public UserIdAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserIdAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public UserIdAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
