package com.paeez.core.services.exceptions;

/**
 * Created by Shrikant on 1/4/15.
 */
public class GroupBetsDoesNotExistsException extends RuntimeException {
    public GroupBetsDoesNotExistsException() {
    }

    public GroupBetsDoesNotExistsException(String message) {
        super(message);
    }

    public GroupBetsDoesNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public GroupBetsDoesNotExistsException(Throwable cause) {
        super(cause);
    }

    public GroupBetsDoesNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
