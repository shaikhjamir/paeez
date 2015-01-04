package com.paeez.test.core.repositories;

import com.paeez.Application;
import com.paeez.core.model.GenericBet;
import com.paeez.core.model.GroupAdminUsers;
import com.paeez.core.model.GroupUsers;
import com.paeez.core.model.User;
import com.paeez.core.repositories.mongo.GlobalBetRepository;
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
public class GenericBetTest { //extends BaseModelTest {

	private static final int NO_OF_COLUMNS = 4;

	@Autowired
	public GlobalBetRepository globalBetRepo;

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
	public void seedGlobalBetData() {

		try {
			List<GenericBet> genericBetList = globalBetRepo.findAll() ;
			
			GenericBet bet = genericBetList.get(0) ;
			Assert.assertNotNull(bet);
			Assert.assertTrue(bet.getOptions().length > 0 );
			Assert.assertTrue(bet.getDescription() != null );
			Assert.assertTrue(bet.getClosingTime() > 0  && bet.getStatus() != null );
		} catch (Exception ex) {

			ex.printStackTrace();
			Assert.fail("Failed: " + ex.getMessage());
		}
	}
	
}
