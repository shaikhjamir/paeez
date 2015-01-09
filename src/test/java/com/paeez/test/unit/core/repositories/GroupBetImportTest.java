package com.paeez.test.unit.core.repositories;

import com.paeez.Application;
import com.paeez.core.services.impl.GroupBetImportServiceImpl;
import com.paeez.core.repositories.mongo.GlobalBetRepository;
import com.paeez.core.repositories.mongo.GroupBetImportRepository;
import com.paeez.core.repositories.mongo.GroupRepository;
import com.paeez.core.repositories.mongo.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class GroupBetImportTest { //} extends BaseModelTest {

	// private static final int NO_OF_COLUMNS = 4;

	@Autowired
	public GlobalBetRepository globalBetRepo;

	@Autowired
	public GroupBetImportRepository groupBetImportRepo;
	
	@Autowired
	protected GroupBetImportServiceImpl groupBetImportBO ;
	
	@Autowired
	public UserRepository userRepo;

	@Autowired
	public GroupRepository groupRepo;
	
	@Test
	public void dropTable() {

		//super.dropTable(GroupBetImport.class);
	}
	
	
	@Test
	public void seedGroupBetImportData() {

		try {
			dropTable();
			GenericBetTest betTest = new GenericBetTest() ;
			betTest.globalBetRepo = globalBetRepo ;
			betTest.seedGlobalBetData();
		} catch (Exception ex) {

			ex.printStackTrace();
			Assert.fail("Failed: " + ex.getMessage());
		}
	}
	
	
	@Test
	public void importTest() {
		
//		try {
//			seedGroupBetImportData() ;
//
//			WSResponse response = new WSResponse() ;
//
//			User usr = userRepo.findAll().get(0) ;
//			Group grp = groupRepo.findAll().get(0) ;
//			GenericBet genericBet = globalBetRepo.findAll().get(0) ;
//
//			groupBetImportBO.importBet(response, usr.getId(), grp.getId(), genericBet.getId() ) ;
//			List<String> messages = response.getMessages() ;
//
//			Assert.assertNotNull(messages);
//			Assert.assertTrue(messages.size() > 0 );
//			Assert.assertTrue(messages.size() > 0 );
//
//			System.out.println("Jamir::=" + messages);
//		} catch (Exception ex) {
//
//			ex.printStackTrace();
//			Assert.fail("Failed: " + ex.getMessage());
//		}
	}
	
}
