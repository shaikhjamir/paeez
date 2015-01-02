package com.paeez.core.services.impl;

import com.paeez.core.model.Group;
import com.paeez.core.model.GroupAdminUsers;
import com.paeez.core.model.User;
import com.paeez.rest.responses.ResponseConstants;
import com.paeez.rest.responses.WSResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("groupAdminUsersBO")
public class GroupAdminUsersService extends BaseService {

	public boolean addAdminUsers(WSResponse response, String groupId, List<String> emailAddressList) {
		
		if (emailAddressList == null || emailAddressList.size() == 0 ) {
			
			return false ;
		}
		
		for (String userEmail : emailAddressList) {
		
			User usr = userRepo.findByEmailAddress(userEmail) ;
			if (usr == null ) {
				
				response.error("Skipping: User not found( might not be registered): " + userEmail ) ;
				continue ;
			}

			GroupAdminUsers searchUsers = groupAdminUsersRepo.findByGroupIdAndUserEmailAddress(groupId, usr.getEmailAddress() ) ;
			if (searchUsers != null ) {
				
				response.info("Skipping: User is already Admin: " + usr.getEmailAddress() ) ;
				continue ;
			}
			
			Group grp = groupRepo.findOne(groupId) ;
			if (grp == null ) {
				
				response.error("Group not found: " + groupId ) ;
				return false ;
			}
			GroupAdminUsers adminUsers = new GroupAdminUsers() ;
			adminUsers.setGroupId(groupId);
			adminUsers.setUserEmailAddress(userEmail);
			adminUsers.setAddedOn(System.currentTimeMillis());
			groupAdminUsersRepo.save(adminUsers) ;
			
			response.info("Success: User added as Admin: " + usr.getEmailAddress() ) ;
		}
		
		return true ;	
	}
	
	/**
	 * Get groups where passed User is the admin
	 * @param response
	 * @param userId
	 * @return
	 */
	public boolean getMyAdminGroups(WSResponse response, String userId) {
		
		if (userId == null) {
			
			return false ;
		}
		
		User usr = userRepo.findOne(userId) ;
		if (usr == null ) {
			
			response.error("User not found");
			return false ;
		}
		
		List<GroupAdminUsers> searchGroups = groupAdminUsersRepo.findByUserEmailAddress(usr.getEmailAddress() ) ;
		response.put(ResponseConstants.ADMIN_GROUPS, searchGroups);
		return true ;	
	}
	
	
	public boolean removeAdminUsers(WSResponse response, String groupId, List<String> emailAddressList, String userId) {
		
		if (emailAddressList == null || emailAddressList.size() == 0 ) {
			
			return false ;
		}
		
		for (String userEmail : emailAddressList) {
		
			User usr = userRepo.findByEmailAddress(userEmail) ;
			if (usr == null ) {
				
				response.error("Skipping: User not found( might not be registered): " + userEmail ) ;
				continue ;
			}

			if (usr.getId().equals(userId)) {
				
				response.info("Skipping: Cannot removed self as Admin: " + usr.getEmailAddress() ) ;
				continue ;
			}
			GroupAdminUsers searchUsers = groupAdminUsersRepo.findByGroupIdAndUserEmailAddress(groupId, usr.getEmailAddress() ) ;
			if (searchUsers != null ) {
				
				groupAdminUsersRepo.delete(searchUsers);
				response.info("Success: User removed as Admin: " + usr.getEmailAddress() ) ;
				continue ;
			}
		}
		
		return true ;	
	}
}
