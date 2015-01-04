package com.paeez.test.core.repositories;

import com.paeez.Application;
import com.paeez.core.model.User;
import com.paeez.core.repositories.mongo.UserRepository;
import com.paeez.test.utilities.SetupUtil;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class UsersTest {
	public static final String TEST_EMAIL_ADDRESS = "test@test.com";

	@Autowired
	public UserRepository userRepo;

	@BeforeClass
	public static void beforeAll() {
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
		SetupUtil util = new SetupUtil(mongoOperations);
		util.setupRepo();
		if(!util.checkSeed(User.class))
			Assert.fail("Error in seeding data");
	}

	@Test
	public void testUserRepo() {

		try {
			User user = userRepo.findByEmailAddress(TEST_EMAIL_ADDRESS);
			Assert.assertNotNull(user);
			Assert.assertEquals("active", user.getStatus());
			Assert.assertEquals("Test", user.getName());

		} catch (Exception ex) {
			ex.printStackTrace();
			Assert.fail("Failed: " + ex.getMessage());
		}
	}

}
