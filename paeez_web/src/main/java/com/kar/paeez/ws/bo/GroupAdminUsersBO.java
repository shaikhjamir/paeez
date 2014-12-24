package com.kar.paeez.ws.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.kar.paeez.ws.model.GroupAdminUsers;
import com.kar.paeez.ws.repo.mongo.GroupAdminUsersRepository;

public class GroupAdminUsersBO extends BaseBO {

	@Autowired
	private GroupAdminUsersRepository groupAdminUsersRepo;

	public boolean addAdminUsers(String group, List<String> users) {
		
		if (users == null || users.size() == 0 ) {
			
			return false ;
		}
		
		for (String user : users) {
		
			GroupAdminUsers searchUsers = groupAdminUsersRepo.findByGroupAndUser(group, user) ;
			if (searchUsers != null ) {
				
				continue ;
			}
			GroupAdminUsers adminUsers = new GroupAdminUsers() ;
			adminUsers.setGroup(group);
			adminUsers.setUser(user);
			groupAdminUsersRepo.save(adminUsers) ;
		}
		
		return true ;	
	}
}
