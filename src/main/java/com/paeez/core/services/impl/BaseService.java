package com.paeez.core.services.impl;

import java.util.List;

import com.paeez.core.model.Group;
import com.paeez.core.model.User;
import com.paeez.core.repositories.mongo.*;
import com.paeez.core.services.exceptions.GroupDoesNotExistsException;
import com.paeez.core.services.exceptions.UserDoesNotExistsException;
import com.paeez.core.services.exceptions.UserNotInGroupException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;


abstract public class BaseService {
	
	@Autowired
	protected GroupAdminUsersRepository groupAdminUsersRepo;

	@Autowired
	protected GroupUsersRepository groupUsersRepo;
	
	@Autowired
	protected UserRepository userRepo;

	@Autowired
	protected GroupRepository groupRepo;

	@Autowired
	protected GroupBetsRepository groupBetsRepository;

	@Autowired
	protected GenericBetRepository genericBetRepository;

	@Autowired
	protected MongoOperations mongoOperations;

	@Autowired
	protected UserBetsRepository userBetsRepository;

	
	/**
	 * The loggedIn user
	 * 
	 */
	protected User loggedInUser ;
	
	
	protected void validateUser(String userId){
	    
		loggedInUser = userRepo.findOne(userId) ;
    	if (loggedInUser == null) {
    		
    	    throw new UserDoesNotExistsException(userId);
    	}
    }
	
	protected void validateUserGroup(String userId, String groupId){
	    
		validateUser(userId) ;
		validateGroup(groupId) ;
    	
		boolean userBelongsToPassedGroup = false ;
		List<Group> userGrps = loggedInUser.getGroups() ;
		
		if (userGrps != null && userGrps.size() > 0 ) {
	        for (Group group : userGrps) {
				if (group.getId().equals(groupId)) {
					userBelongsToPassedGroup = true ;
	        		break;
	        	}
			}
		}
		
        if (!userBelongsToPassedGroup)
        	throw new UserNotInGroupException(userId, groupId);
    }
	
	
	protected Group validateGroup(String groupId) {
    	
    	Group group = groupRepo.findOne(groupId) ;
        if (group == null) {
            throw new GroupDoesNotExistsException(groupId);
        }
        return group ;
    }
}

