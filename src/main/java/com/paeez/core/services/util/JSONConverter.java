package com.paeez.core.services.util;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paeez.core.services.constants.BetOptions;

public class JSONConverter {

	public static void main(String[] args) {
		Map<BetOptions, Long> bets = new HashMap<>() ;
		bets.put(BetOptions.OPTIONA, 11l) ;
		bets.put(BetOptions.OPTIONB, 12l) ;
		bets.put(BetOptions.OPTIONC, 13l) ;
			ObjectMapper mapper = new ObjectMapper();
			try {
				System.out.println(mapper.writeValueAsString(bets));
			} catch (Exception e) {
				e.printStackTrace();
		 
			} 
	}
}
