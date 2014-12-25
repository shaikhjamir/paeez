package com.kar.paeez.ws.repo.model;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.kar.paeez.ws.model.Group;
import com.kar.paeez.ws.model.GroupAdminUsers;
import com.kar.paeez.ws.model.User;
import com.kar.paeez.ws.repo.mongo.GroupAdminUsersRepository;
import com.kar.paeez.ws.repo.mongo.GroupRepository;
import com.kar.paeez.ws.repo.mongo.UserRepository;

public class GroupAdminUsersTest extends BaseModelTest {

	@Autowired
	private GroupRepository groupRepo;

	// We need to define this here, since only this class will be autowired, when we do new UsersTest it is not aware of the AutoWiring 
	@Autowired
	public UserRepository userRepo;
	
	
	@Autowired
	private GroupAdminUsersRepository groupAdminUsersRepo;

	
	@Test
	public void dropTable() {

		super.dropTable(GroupAdminUsers.class);
	}
	
	
	@Test
	public void seedTestUserData() {

		try {
			dropTable();
			
			GroupsTest groupsTest = new GroupsTest() ;
			groupsTest.groupRepo = groupRepo ;
			groupsTest.userRepo = userRepo ;
			groupsTest.seedTestUserData();
			
			User firstUser = userRepo.findByEmailAddress(UsersTest.TEST_EMAIL_ADDRESS);
			Assert.assertNotNull(firstUser);
			
			for (Group grp : firstUser.getGroups()) {
				
				GroupAdminUsers adminUsers = new GroupAdminUsers() ;
				adminUsers.setGroup(grp.getId());
				adminUsers.setUserEmailAddress(firstUser.getEmailAddress());
				groupAdminUsersRepo.save(adminUsers) ;
			}
			
			
			List<GroupAdminUsers> allGrpUsers = groupAdminUsersRepo.findAll() ;
			Assert.assertNotNull(allGrpUsers);
			for (GroupAdminUsers groupAdminUsers : allGrpUsers) {
				
				Assert.assertEquals(firstUser.getEmailAddress(), groupAdminUsers.getUserEmailAddress());
			}
			
		} catch (Exception ex) {

			ex.printStackTrace();
			Assert.fail("Failed: " + ex.getMessage());
		}
	}
	

}
