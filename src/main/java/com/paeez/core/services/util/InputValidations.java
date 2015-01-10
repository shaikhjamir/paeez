package com.paeez.core.services.util;

import java.util.List;

import com.paeez.core.model.Group;
import com.paeez.core.model.User;
import com.paeez.core.repositories.mongo.*;
import com.paeez.core.services.exceptions.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Shrikant on 1/4/15.
 */
public class InputValidations {

    @Autowired
    private static GroupRepository groupRepository;
    @Autowired
    private static UserRepository userRepository;
    @Autowired
    private static GroupBetsRepository groupBetsRepository;
    @Autowired
    private static GroupUsersRepository groupUsersRepository;
    @Autowired
    private static UserBetsRepository userBetsRepository;

    public static void validateInputIdForNull(String errorMessage, String ... Ids) {
        for (String id : Ids){
            if (id == null || id.isEmpty())
                throw new InputIdNullOrEmptyException(errorMessage);
        }
    }

    public static Group validateGroupExists(String groupId) {
    	
    	Group group = groupRepository.findOne(groupId) ;
        if (group == null)
            throw new GroupDoesNotExistsException("Group not found");
        return group ;
    }

    public static void validateUserBelongsToGroup(String userId, String groupId){
    
    	User usr = userRepository.findOne(userId) ;
    	validateUserBelongsToGroup(usr, groupId) ;
    }
    
    public static void validateUserBelongsToGroup(User usr, String groupId){
        
    	List<Group> usersGroupus = usr.getGroups() ;
        for (Group group : usersGroupus) {
			
        	if (group.getId().equals(groupId)) {
        		
        		InputValidations.validateForNull(null, usr.getName() +" is not part of this group, please verify his groups");
        		break ;
        	}
		}
    }
    
    public static void validateDuplicateGroup(String groupId, String errorMessage) {
        if (groupRepository.findOne(groupId) != null)
            throw new GroupDoesNotExistsException(errorMessage);
    }

    public static void validateUserExists(String userId) {

    	if (userRepository.findOne(userId) == null)
            throw new UserDoesNotExistsException("User not found");
    }

    public static void validateDuplicateUser(String userId) {

    	if (userRepository.findOne(userId) != null)
            throw new UserDoesNotExistsException("User not found");
    }

    public static void validateBetsCartExists(String betsCartId, String errorMessage) {
        if (groupBetsRepository.findOne(betsCartId) == null)
            throw new GroupBetsDoesNotExistsException(errorMessage);
    }


    public static void validateForNull(Object object, String errorMessage){
        if (object == null)
            throw new NullArgumentException(errorMessage);
    }
}
