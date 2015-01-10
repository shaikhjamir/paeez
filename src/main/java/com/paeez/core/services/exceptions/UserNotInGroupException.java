package com.paeez.core.services.exceptions;

public class UserNotInGroupException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    public UserNotInGroupException(String userId, String groupId) {
        super("User [" + userId +"] does not belog to the passed group[" + groupId + "]");
    }
}
