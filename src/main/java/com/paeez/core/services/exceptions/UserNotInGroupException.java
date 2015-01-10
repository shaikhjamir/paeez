package com.paeez.core.services.exceptions;

public class UserNotInGroupException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotInGroupException() {
    }

    public UserNotInGroupException(String message) {
        super(message);
    }
}
