package com.paeez.core.services.exceptions;

/**
 * Created by Shrikant on 1/4/15.
 */
public class GroupDoesNotExistsException extends RuntimeException {
    public GroupDoesNotExistsException() {
    }

    public GroupDoesNotExistsException(String message) {
        super(message);
    }

    public GroupDoesNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public GroupDoesNotExistsException(Throwable cause) {
        super(cause);
    }

    public GroupDoesNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
