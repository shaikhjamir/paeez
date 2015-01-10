package com.paeez.core.services.util;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paeez.core.model.UserBets;
import com.paeez.core.services.constants.BetOptions;

public class JSONConverter {

	public static void main(String[] args) {
		
		UserBets bets = new UserBets() ;
		Map<BetOptions, Long> betsOPtion = new HashMap<>() ;
		betsOPtion.put(BetOptions.OPTIONA, 11l) ;
		betsOPtion.put(BetOptions.OPTIONB, 12l) ;
		betsOPtion.put(BetOptions.OPTIONC, 13l) ;
		
		bets.setGenericBetId("genericbetid-gen-bet");
		bets.setGroupId("group-id-grp-1");
		bets.setUserId("user-id-1");
		
		bets.setBetMeasureByOptions(betsOPtion);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println(mapper.writeValueAsString(bets));
		} catch (Exception e) {
			e.printStackTrace();
	 
		} 
	
	}
}
