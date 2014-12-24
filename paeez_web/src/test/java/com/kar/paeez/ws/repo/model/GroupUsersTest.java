package com.kar.paeez.ws.repo.model;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.kar.paeez.ws.model.Group;
import com.kar.paeez.ws.model.GroupUsers;
import com.kar.paeez.ws.model.User;
import com.kar.paeez.ws.repo.mongo.GroupRepository;
import com.kar.paeez.ws.repo.mongo.GroupUsersRepository;
import com.kar.paeez.ws.repo.mongo.UserRepository;

public class GroupUsersTest extends BaseModelTest {

	@Autowired
	private GroupRepository groupRepo;

	// We need to define this here, since only this class will be autowired, when we do new UsersTest it is not aware of the AutoWiring 
	@Autowired
	public UserRepository userRepo;
	
	
	@Autowired
	private GroupUsersRepository groupUsersRepo;

	
	@Test
	public void dropTable() {

		super.dropTable(GroupUsers.class);
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
				
				GroupUsers adminUsers = new GroupUsers() ;
				adminUsers.setGroup(grp);
				adminUsers.setUser(firstUser);
				groupUsersRepo.save(adminUsers) ;
			}
			
			
			List<GroupUsers> allGrpUsers = groupUsersRepo.findAll() ;
			Assert.assertNotNull(allGrpUsers);
			for (GroupUsers groupUsers : allGrpUsers) {
				
				Assert.assertEquals(firstUser.getId(), groupUsers.getUser().getId());
			}
			
		} catch (Exception ex) {

			ex.printStackTrace();
			Assert.fail("Failed: " + ex.getMessage());
		}
	}
	

}
