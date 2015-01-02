package com.paeez.core.services.impl;

import com.paeez.core.model.*;
import com.paeez.rest.responses.ResponseConstants;
import com.paeez.rest.responses.WSResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("GroupBetImportBO")
public class GroupBetImportService extends BaseService {

	public boolean importBet(WSResponse response, String userId, String groupId, String globalBetId) {
		
		if (globalBetId == null || globalBetId.length() == 0 || 
				groupId == null || groupId.length() == 0) {
			
			response.info("Failed: Bet/Group to import not found : " + globalBetId ) ;
			return false ;
		}
		
		
		GlobalBet globalBet = globalBetRepo.findOne(globalBetId) ;
		if (globalBet == null ) {
			
			response.info("Failed: Bet to import not found : " + globalBetId ) ;
			return false ;
		}
		
		GroupBetImport groupBetImport = groupGlobalBetImportRepo.findByGroupIdAndGlobalBet(groupId, globalBet) ;
		if (groupBetImport != null ) {
			
			response.info("Failed: Bet to import already imported for the specified group : " + groupBetImport.getGroupId() ) ;
			return false ;
		}
		
		User usr = userRepo.findOne(userId) ;
		if (usr == null ) {
			
			response.info("Failed: Admin user not found") ;
			return false ;
		}
		
		Group grp = groupRepo.findOne(groupId) ;
		if (grp == null ) {
			
			response.info("Failed: Group not found") ;
			return false ;
		}

		GroupAdminUsers grpAdmin = groupAdminUsersRepo.findByGroupIdAndUserEmailAddress(groupId, usr.getEmailAddress() ) ;
		if (grpAdmin == null ) {
			
			response.info("Failed: Provided User: "+ usr.getEmailAddress() + " is not admin to this Group: "+ grp.getDescription() ) ;
			return false ;
		}
		
		
		GroupBetImport groupBetImportNew = new GroupBetImport() ;
		groupBetImportNew.setGlobalBet(globalBet);
		groupBetImportNew.setGroupId(groupId);
		groupBetImportNew.setImportedByUserEmailAddres(usr.getEmailAddress());
		groupBetImportNew.setImportedOn(System.currentTimeMillis());
		groupGlobalBetImportRepo.save(groupBetImportNew);
		
		response.info("Success: Added Bet to the Group") ;
		return true ;	
	}
	
	public boolean listImportedBets(WSResponse response, String userId, String groupId) {
		
		List<GroupBetImport> groupBetImportList = groupGlobalBetImportRepo.findByGroupId(groupId) ;
		response.put(ResponseConstants.LIST_IMPORTED_BETS, groupBetImportList);
		return true ;
	}
}
