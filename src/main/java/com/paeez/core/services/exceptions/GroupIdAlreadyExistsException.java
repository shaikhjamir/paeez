package com.paeez.core.services.exceptions;

/**
 * Created by Shrikant on 1/4/15.
 */
public class GroupIdAlreadyExistsException extends RuntimeException {
    public GroupIdAlreadyExistsException() {
    }

    public GroupIdAlreadyExistsException(String message) {
        super(message);
    }

    public GroupIdAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public GroupIdAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public GroupIdAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
