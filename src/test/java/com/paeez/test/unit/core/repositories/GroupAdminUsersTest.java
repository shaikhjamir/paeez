package com.paeez.test.unit.core.repositories;

import com.paeez.Application;
import com.paeez.core.model.Group;
import com.paeez.core.model.GroupAdminUsers;
import com.paeez.core.model.GroupUsers;
import com.paeez.core.model.User;
import com.paeez.core.repositories.mongo.GroupAdminUsersRepository;
import com.paeez.core.repositories.mongo.GroupRepository;
import com.paeez.core.repositories.mongo.UserRepository;
import com.paeez.test.utilities.SetupUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class GroupAdminUsersTest { //} extends BaseModelTest {

	@Autowired
	private GroupRepository groupRepo;

	@Autowired
	public UserRepository userRepo;
	
	
	@Autowired
	private GroupAdminUsersRepository groupAdminUsersRepo;

	@Autowired
	private MongoOperations mongoOperations;

	@Before
	public void seedorCheckSeedData() {
		Assert.assertNotNull("Dependency Injection for MongoOperations unsuccessful", mongoOperations);

		if (mongoOperations.collectionExists(User.class)) {
			mongoOperations.dropCollection(User.class);
		}
		if (mongoOperations.collectionExists(User.class)) {
			Assert.fail("Delete of the collection " + User.class + " failed");
		}

		if (mongoOperations.collectionExists(GroupUsers.class)) {
			mongoOperations.dropCollection(GroupUsers.class);
		}
		if (mongoOperations.collectionExists(GroupUsers.class)) {
			Assert.fail("Delete of the collection " + GroupUsers.class + " failed");
		}

		if (mongoOperations.collectionExists(GroupAdminUsers.class)) {
			mongoOperations.dropCollection(GroupAdminUsers.class);
		}
		if (mongoOperations.collectionExists(GroupAdminUsers.class)) {
			Assert.fail("Delete of the collection " + GroupAdminUsers.class + " failed");
		}
		SetupUtil util = new SetupUtil(mongoOperations);
		util.setupRepo();
		if(!util.checkSeed(User.class))
			Assert.fail("Error in seeding data");
	}
	
	@Test
	public void testAdminUserRepo() {

		try {

			User firstUser = userRepo.findByEmailAddress(UsersTest.TEST_EMAIL_ADDRESS);
			Assert.assertNotNull(firstUser);

			Assert.assertNotNull(firstUser.getGroups());

			for (Group grp : firstUser.getGroups()) {
				
				GroupAdminUsers adminUsers = new GroupAdminUsers() ;
				adminUsers.setGroupId(grp.getId());
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
