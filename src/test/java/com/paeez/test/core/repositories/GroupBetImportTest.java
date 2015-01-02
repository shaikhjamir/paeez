package com.paeez.test.core.repositories;

import com.paeez.Application;
import com.paeez.core.services.impl.GroupBetImportService;
import com.paeez.core.model.GlobalBet;
import com.paeez.core.model.Group;
import com.paeez.core.model.GroupBetImport;
import com.paeez.core.model.User;
import com.paeez.core.repositories.mongo.GlobalBetRepository;
import com.paeez.core.repositories.mongo.GroupBetImportRepository;
import com.paeez.core.repositories.mongo.GroupRepository;
import com.paeez.core.repositories.mongo.UserRepository;
import com.paeez.rest.responses.WSResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

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
	protected GroupBetImportService groupBetImportBO ;
	
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
			GlobalBetTest betTest = new GlobalBetTest() ;
			betTest.globalBetRepo = globalBetRepo ;
			betTest.seedGlobalBetData();
		} catch (Exception ex) {

			ex.printStackTrace();
			Assert.fail("Failed: " + ex.getMessage());
		}
	}
	
	
	@Test
	public void importTest() {
		
		try {
			seedGroupBetImportData() ;
			
			WSResponse response = new WSResponse() ;
			
			User usr = userRepo.findAll().get(0) ;
			Group grp = groupRepo.findAll().get(0) ;
			GlobalBet globalBet = globalBetRepo.findAll().get(0) ;
			
			groupBetImportBO.importBet(response, usr.getId(), grp.getId(), globalBet.getId() ) ;
			List<String> messages = response.getMessages() ;
			
			Assert.assertNotNull(messages);
			Assert.assertTrue(messages.size() > 0 );
			Assert.assertTrue(messages.size() > 0 );
			
			System.out.println("Jamir::=" + messages);
		} catch (Exception ex) {

			ex.printStackTrace();
			Assert.fail("Failed: " + ex.getMessage());
		}
	}
	
}
