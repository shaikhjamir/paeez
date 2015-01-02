package com.kar.paeez.ws.repo.model;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseLong;
import org.supercsv.cellprocessor.ift.CellProcessor;

import com.kar.paeez.ws.model.GlobalBet;
import com.kar.paeez.ws.repo.mongo.GlobalBetRepository;

public class GlobalBetTest extends BaseModelTest {

	private static final int NO_OF_COLUMNS = 4;

	@Autowired
	public GlobalBetRepository globalBetRepo;

	@Test
	public void dropTable() {

		super.dropTable(GlobalBet.class);
	}
	
	
	@Test
	public void seedGlobalBetData() {

		try {
			dropTable();
			
			final CellProcessor[] processors = new CellProcessor[] { 
					new Optional(),
					new Optional(),
					new Optional(new ParseLong()),
					new Optional()
			} ;
			String path = this.getClass().getResource("/testdata/GlobalBet.csv").getPath();
			insertFromCSV(path, GlobalBet.class, NO_OF_COLUMNS, processors);
			
			List<GlobalBet> globalBetList = globalBetRepo.findAll() ;
			
			GlobalBet bet = globalBetList.get(0) ;
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
