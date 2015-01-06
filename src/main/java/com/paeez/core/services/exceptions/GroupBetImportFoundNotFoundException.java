package com.paeez.core.services.exceptions;

/**
 * Created by Shrikant on 1/4/15.
 */
public class GroupBetImportFoundNotFoundException extends RuntimeException {
    public GroupBetImportFoundNotFoundException() {
    }

    public GroupBetImportFoundNotFoundException(String message) {
        super(message);
    }

    public GroupBetImportFoundNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public GroupBetImportFoundNotFoundException(Throwable cause) {
        super(cause);
    }

    public GroupBetImportFoundNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
