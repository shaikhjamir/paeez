package com.paeez.core.services.exceptions;

/**
 * Created by Shrikant on 1/4/15.
 */
public class GroupIdDoesNotExistsException extends RuntimeException {
    public GroupIdDoesNotExistsException() {
    }

    public GroupIdDoesNotExistsException(String message) {
        super(message);
    }

    public GroupIdDoesNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public GroupIdDoesNotExistsException(Throwable cause) {
        super(cause);
    }

    public GroupIdDoesNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
