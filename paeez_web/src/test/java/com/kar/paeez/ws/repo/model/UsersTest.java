package com.kar.paeez.ws.repo.model;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.kar.paeez.ws.model.User;
import com.kar.paeez.ws.repo.mongo.UserRepository;


public class UsersTest extends BaseModelTest {

	private static final int NO_OF_COLUMNS = 3;

	public static final String TEST_EMAIL_ADDRESS = "test@test.com";

	@Autowired
	public UserRepository userRepo;

	@Test
	public void dropTable() {

		super.dropTable(User.class);
	}

	@Test
	public void seedTestUserData() {

		try {
			dropTable();
			String path = this.getClass().getResource("/testdata/Users.csv").getPath();
			insertFromCSV(path, User.class, NO_OF_COLUMNS, null);

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
