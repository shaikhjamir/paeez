package com.paeez.rest.controllers;

import com.paeez.core.services.impl.GroupBetImportService;
import com.paeez.core.model.GroupBetImport;
import com.paeez.rest.responses.ResponseConstants;
import com.paeez.rest.responses.WSResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GroupBetImportController {

	@Autowired
	private GroupBetImportService groupBetImportBO;
	
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
