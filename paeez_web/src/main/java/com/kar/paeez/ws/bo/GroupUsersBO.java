package com.kar.paeez.ws.bo;

import java.util.List;

import org.springframework.stereotype.Component;

import com.kar.paeez.ws.model.Group;
import com.kar.paeez.ws.model.GroupUsers;
import com.kar.paeez.ws.model.User;
import com.kar.paeez.ws.response.ResponseConstants;
import com.kar.paeez.ws.response.WSResponse;

@Component("GroupUsersBO")
public class GroupUsersBO extends BaseBO {

	public boolean addUsers(WSResponse response, String groupId, List<String> emailAddressList) {
		
		if (emailAddressList == null || emailAddressList.size() == 0 ) {
			
			return false ;
		}
		
		for (String userEmail : emailAddressList) {
		
			User usr = userRepo.findByEmailAddress(userEmail) ;
			if (usr == null ) {
				
				response.error("Skipping: User not found( might not be registered): " + userEmail ) ;
				continue ;
			}

			GroupUsers searchUsers = groupUsersRepo.findByGroupAndUserEmailAddress(groupId, usr.getEmailAddress() ) ;
			if (searchUsers != null ) {
				
				response.info("Skipping: User is already Admin: " + usr.getEmailAddress() ) ;
				continue ;
			}
			
			Group grp = groupRepo.findOne(groupId) ;
			if (grp == null ) {
				
				response.error("Group not found: " + groupId ) ;
				return false ;
			}
			GroupUsers adminUsers = new GroupUsers() ;
			adminUsers.setGroupId(groupId);
			adminUsers.setUserEmailAddress(userEmail);
			adminUsers.setAddedOn(System.currentTimeMillis());
			groupUsersRepo.save(adminUsers) ;
			
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
	public boolean getMyGroups(WSResponse response, String userId) {
		
		if (userId == null) {
			
			return false ;
		}
		
		User usr = userRepo.findOne(userId) ;
		if (usr == null ) {
			
			response.error("User not found");
			return false ;
		}
		
		List<GroupUsers> searchGroups = groupUsersRepo.findByUserEmailAddress(usr.getEmailAddress() ) ;
		response.put(ResponseConstants.ADMIN_GROUPS, searchGroups);
		return true ;	
	}
	
	
	public boolean removeUsers(WSResponse response, String groupId, List<String> emailAddressList, String userId) {
		
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
			GroupUsers searchUsers = groupUsersRepo.findByGroupAndUserEmailAddress(groupId, usr.getEmailAddress() ) ;
			if (searchUsers != null ) {
				
				groupUsersRepo.delete(searchUsers);
				response.info("Success: User removed as Admin: " + usr.getEmailAddress() ) ;
				continue ;
			}
		}
		
		return true ;	
	}
}
