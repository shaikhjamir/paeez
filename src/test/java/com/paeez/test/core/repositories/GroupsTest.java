package com.paeez.test.core.repositories;

import com.paeez.Application;
import com.paeez.core.model.Group;
import com.paeez.core.model.GroupUsers;
import com.paeez.core.model.User;
import com.paeez.core.repositories.mongo.GroupRepository;
import com.paeez.core.repositories.mongo.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseLong;
import org.supercsv.cellprocessor.ift.CellProcessor;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class GroupsTest { //extends BaseModelTest {

	private static final int NO_OF_COLUMNS = 4;

	@Autowired
	public GroupRepository groupRepo;

	// We need to define this here, since only this class will be autowired, when we do new UsersTest it is not aware of the AutoWiring 
	@Autowired
	public UserRepository userRepo;
	
	@Test
	public void dropTable() {

		//super.dropTable(Group.class);
	}

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
		SetupUtil util = new SetupUtil(mongoOperations);
		util.setupRepo();
		if(!util.checkSeed(User.class))
			Assert.fail("Error in seeding data");
	}
	
	@Test
	public void testGroupRepo() {

		try {
			// First User Test
			List<Group> groupList = groupRepo.findAll() ; 
			List<User> userList = userRepo.findAll() ;

			User usr = userList.get(0) ;
			Assert.assertNotNull(usr);
			usr.setGroups(groupList);
			userRepo.save(usr) ;

			User firstUser = userRepo.findByEmailAddress(UsersTest.TEST_EMAIL_ADDRESS);
			Assert.assertTrue(firstUser.getGroups().size() > 0 );
			Assert.assertTrue(firstUser.getGroups().get(0) != null );
			Assert.assertTrue(firstUser.getGroups().get(0).getId() != null && firstUser.getGroups().get(0).getId().length() > 0 );

		} catch (Exception ex) {

			ex.printStackTrace();
			Assert.fail("Failed: " + ex.getMessage());
		}
	}
	
}
