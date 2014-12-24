package com.kar.paeez.ws.repo.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseLong;
import org.supercsv.cellprocessor.ift.CellProcessor;

import com.kar.paeez.ws.model.Group;
import com.kar.paeez.ws.model.User;
import com.kar.paeez.ws.repo.mongo.GroupRepository;
import com.kar.paeez.ws.repo.mongo.UserRepository;

public class GroupsTest extends BaseModelTest {

	private static final int NO_OF_COLUMNS = 4;

	@Autowired
	private GroupRepository groupRepo;

	// We need to define this here, since only this class will be autowired, when we do new UsersTest it is not aware of the AutoWiring 
	@Autowired
	public UserRepository userRepo;
	
	@Test
	public void dropTable() {

		super.dropTable(Group.class);
	}
	
	
	@Test
	public void seedTestUserData() {

		try {
			dropTable();
			
			final CellProcessor[] processors = new CellProcessor[] { 
					new Optional(),
					new Optional(),
					new Optional(new ParseLong()),
					new Optional()
			} ;
			String path = this.getClass().getResource("/testdata/Groups.csv").getPath();
			insertFromCSV(path, Group.class, NO_OF_COLUMNS, processors);
			
			UsersTest userData = new UsersTest() ;
			userData.userRepo = userRepo ;
			userData.seedTestUserData();
			
			
			
			// First User Test
			List<Group> groupList = groupRepo.findAll() ; 
			List<User> userList = userRepo.findAll() ;
			
			User usr = userList.get(0) ;
			Assert.assertNotNull(usr);
			usr.setGroups(groupList);
			
			for (Group grp : groupList) {
				
				List<User> adminList = grp.getAdminUsers() ;
				if (adminList == null ) {
					
					adminList = new ArrayList<>() ;
					grp.setAdminUsers(adminList);
				}
				List<User> usList = grp.getUsers() ;
				if (usList == null ) {
					
					usList = new ArrayList<>() ;
					grp.setUsers(usList);
				}

				grp.getAdminUsers().add(usr);
				grp.getUsers().add(usr) ;
				groupRepo.save(grp) ;
			}
			
			userRepo.save(usr) ;
			
			System.out.println("Done saving...");
			User firstUser = userRepo.findByEmailAddress(UsersTest.TEST_EMAIL_ADDRESS);
			// System.out.println(firstUser);
			
		} catch (Exception ex) {

			ex.printStackTrace();
			Assert.fail("Failed: " + ex.getMessage());
		}
	}
	
}
