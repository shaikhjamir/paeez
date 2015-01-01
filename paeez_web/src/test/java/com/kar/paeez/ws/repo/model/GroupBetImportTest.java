package com.kar.paeez.ws.repo.model;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseLong;
import org.supercsv.cellprocessor.ift.CellProcessor;

import com.kar.paeez.ws.bo.GroupBetImportBO;
import com.kar.paeez.ws.model.GlobalBet;
import com.kar.paeez.ws.model.Group;
import com.kar.paeez.ws.model.GroupBetImport;
import com.kar.paeez.ws.model.User;
import com.kar.paeez.ws.repo.mongo.GlobalBetRepository;
import com.kar.paeez.ws.repo.mongo.GroupBetImportRepository;
import com.kar.paeez.ws.repo.mongo.GroupRepository;
import com.kar.paeez.ws.repo.mongo.UserRepository;
import com.kar.paeez.ws.response.WSResponse;

public class GroupBetImportTest extends BaseModelTest {

	// private static final int NO_OF_COLUMNS = 4;

	@Autowired
	public GlobalBetRepository globalBetRepo;

	@Autowired
	public GroupBetImportRepository groupBetImportRepo;
	
	@Autowired
	protected GroupBetImportBO groupBetImportBO ;
	
	@Autowired
	public UserRepository userRepo;

	@Autowired
	public GroupRepository groupRepo;
	
	@Test
	public void dropTable() {

		super.dropTable(GroupBetImport.class);
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
