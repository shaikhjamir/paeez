package com.kar.paeez.ws.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kar.paeez.ws.bo.GroupBetImportBO;
import com.kar.paeez.ws.model.GroupBetImport;
import com.kar.paeez.ws.response.ResponseConstants;
import com.kar.paeez.ws.response.WSResponse;

@RestController
public class GroupBetImportController {

	@Autowired
	private GroupBetImportBO groupBetImportBO;
	
	@RequestMapping(value="/grpBetImport/{userId}/{grpId}/import/{globalBetId}")
    public List<String> importGlobalBet(@PathVariable("userId") String userId, @PathVariable("grpId") String grpId, @PathVariable("globalBetId") String globalBetId) {

		WSResponse response = new WSResponse() ;
		groupBetImportBO.importBet(response, userId, grpId, globalBetId) ;
		return response.getMessages() ;
    }

	@RequestMapping(value="/grpBetImport/{userId}/{grpId}/list")
    public List<GroupBetImport> listImportedBets(@PathVariable("userId") String userId, @PathVariable("grpId") String grpId) {

		WSResponse response = new WSResponse() ;
		groupBetImportBO.listImportedBets(response, userId, grpId) ;
		return (List<GroupBetImport>)response.get(ResponseConstants.LIST_IMPORTED_BETS) ;
    }

}
