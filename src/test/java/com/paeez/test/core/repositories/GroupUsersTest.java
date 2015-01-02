package com.paeez.test.core.repositories;

import com.paeez.Application;
import com.paeez.core.model.Group;
import com.paeez.core.model.GroupUsers;
import com.paeez.core.model.User;
import com.paeez.core.repositories.mongo.GroupRepository;
import com.paeez.core.repositories.mongo.GroupUsersRepository;
import com.paeez.core.repositories.mongo.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
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
public class GroupUsersTest { //extends BaseModelTest {

	@Autowired
	private GroupRepository groupRepo;

	// We need to define this here, since only this class will be autowired, when we do new UsersTest it is not aware of the AutoWiring 
	@Autowired
	public UserRepository userRepo;
	
	
	@Autowired
	private GroupUsersRepository groupUsersRepo;


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

		if (mongoOperations.collectionExists(Group.class)) {
			mongoOperations.dropCollection(Group.class);
		}
		if (mongoOperations.collectionExists(Group.class)) {
			Assert.fail("Delete of the collection " + Group.class + " failed");
		}

		if (mongoOperations.collectionExists(GroupUsers.class)) {
			mongoOperations.dropCollection(GroupUsers.class);
		}
		if (mongoOperations.collectionExists(GroupUsers.class)) {
			Assert.fail("Delete of the collection " + GroupUsers.class + " failed");
		}
		SetupUtil util = new SetupUtil(mongoOperations);
		util.setupRepo();
		if(!util.checkSeed(User.class))
			Assert.fail("Error in seeding data");
	}
	
	@Test
	public void testGroupUserRepo() {

		try {

			User firstUser = userRepo.findByEmailAddress(UsersTest.TEST_EMAIL_ADDRESS);
			Assert.assertNotNull(firstUser);
			
			for (Group grp : firstUser.getGroups()) {
				
				GroupUsers adminUsers = new GroupUsers() ;
				adminUsers.setGroupId(grp.getId());
				adminUsers.setUserEmailAddress(firstUser.getEmailAddress() );
				groupUsersRepo.save(adminUsers) ;
			}

			List<GroupUsers> allGrpUsers = groupUsersRepo.findAll() ;
			Assert.assertNotNull(allGrpUsers);
			for (GroupUsers groupUsers : allGrpUsers) {
				
				Assert.assertEquals(firstUser.getId(), groupUsers.getGroupId());
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			Assert.fail("Failed: " + ex.getMessage());
		}
	}
	

}
