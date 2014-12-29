package com.kar.paeez.ws.bo;

import java.util.List;

import org.springframework.stereotype.Component;

import com.kar.paeez.ws.model.Group;
import com.kar.paeez.ws.model.GroupAdminUsers;
import com.kar.paeez.ws.model.User;
import com.kar.paeez.ws.response.ResponseConstants;
import com.kar.paeez.ws.response.WSResponse;

@Component("groupAdminUsersBO")
public class GroupAdminUsersBO extends BaseBO {

	public boolean addAdminUsers(WSResponse response, String group, List<String> usersEmaiAdderess) {
		
		if (usersEmaiAdderess == null || usersEmaiAdderess.size() == 0 ) {
			
			return false ;
		}
		
		for (String userEmail : usersEmaiAdderess) {
		
			User usr = userRepo.findByEmailAddress(userEmail) ;
			if (usr == null ) {
				
				response.error("Skipping: User not found( might not be registered): " + userEmail ) ;
				continue ;
			}

			GroupAdminUsers searchUsers = groupAdminUsersRepo.findByGroupAndUserEmailAddress(group, usr.getEmailAddress() ) ;
			if (searchUsers != null ) {
				
				response.info("Skipping: User is already Admin: " + usr.getEmailAddress() ) ;
				continue ;
			}
			
			Group grp = groupRepo.findOne(group) ;
			if (grp == null ) {
				
				response.error("Group not found: " + group ) ;
				return false ;
			}
			GroupAdminUsers adminUsers = new GroupAdminUsers() ;
			adminUsers.setGroup(group);
			adminUsers.setUserEmailAddress(userEmail);
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
}
