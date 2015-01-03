package com.paeez.core.services.impl;

import com.paeez.core.model.*;
import com.paeez.core.repositories.mongo.GroupBetImportRepository;
import com.paeez.core.services.api.GroupBetImportService;
import com.paeez.rest.responses.ResponseConstants;
import com.paeez.rest.responses.WSResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GroupBetImportServiceImpl extends BaseService implements GroupBetImportService{

	private GroupBetImportRepository groupBetImportRepository;

	@Autowired
	public GroupBetImportServiceImpl(GroupBetImportRepository groupBetImportRepository) {
		this.groupBetImportRepository = groupBetImportRepository;
	}
	@Override
	public GroupBetImport findByGroupIdAndBetsCartId(String groupId, String betsCartId) {
		return groupBetImportRepository.findByGroupIdAndBetsCartId(groupId, betsCartId);
	}

	@Override
	public GroupBetImport findByGroupId(String groupId) {
		return groupBetImportRepository.findByGroupId(groupId);
	}

//	public void update(String groupId, String betsCartId, String importedByUserEmailAddress) {
//		if(validArguments(groupId, betsCartId, importedByUserEmailAddress)) {
//			GroupBetImport groupBetImport = new GroupBetImport();
//			groupBetImport.setBetsCartId(betsCartId);
//			groupBetImport.setGroupId(groupId);
//			groupBetImport.setImportedByUserEmailAddress(importedByUserEmailAddress);
//			groupBetImport.setImportedOn(new Date());
//
//			groupBetImportRepository.save(groupBetImport);
//		}
//	}

	@Override
	public void update(GroupBetImport groupBetImport) {
		groupBetImportRepository.save(groupBetImport);
	}
	@Override
	public List<GroupBetImport> findByAll() {
		return groupBetImportRepository.findAll();
	}

	private boolean validArguments(String groupId, String betsCartId, String importedByUserEmailAddress) {

		//TODO: add logging and exception handling
		if (groupId == null || groupId.isEmpty())
			return false;
		if (betsCartId == null || betsCartId.isEmpty())
			return false;
		if (importedByUserEmailAddress == null || importedByUserEmailAddress.isEmpty())
			return false;

		return true;
	}
	//	public boolean importBet(WSResponse response, String userId, String groupId, String globalBetId) {
//
//		if (globalBetId == null || globalBetId.length() == 0 ||
//				groupId == null || groupId.length() == 0) {
//
//			response.info("Failed: Bet/Group to import not found : " + globalBetId ) ;
//			return false ;
//		}
//
//
//		GenericBet genericBet = globalBetRepo.findOne(globalBetId) ;
//		if (genericBet == null ) {
//
//			response.info("Failed: Bet to import not found : " + globalBetId ) ;
//			return false ;
//		}
//
//		GroupBetImport groupBetImport = groupGlobalBetImportRepo.findByGroupIdAndGlobalBet(groupId, genericBet) ;
//		if (groupBetImport != null ) {
//
//			response.info("Failed: Bet to import already imported for the specified group : " + groupBetImport.getGroupId() ) ;
//			return false ;
//		}
//
//		User usr = userRepo.findOne(userId) ;
//		if (usr == null ) {
//
//			response.info("Failed: Admin user not found") ;
//			return false ;
//		}
//
//		Group grp = groupRepo.findOne(groupId) ;
//		if (grp == null ) {
//
//			response.info("Failed: Group not found") ;
//			return false ;
//		}
//
//		GroupAdminUsers grpAdmin = groupAdminUsersRepo.findByGroupIdAndUserEmailAddress(groupId, usr.getEmailAddress() ) ;
//		if (grpAdmin == null ) {
//
//			response.info("Failed: Provided User: "+ usr.getEmailAddress() + " is not admin to this Group: "+ grp.getDescription() ) ;
//			return false ;
//		}
//
//
//		GroupBetImport groupBetImportNew = new GroupBetImport() ;
//		groupBetImportNew.setBetsCart(genericBet);
//		groupBetImportNew.setGroupId(groupId);
//		groupBetImportNew.setImportedByUserEmailAddress(usr.getEmailAddress());
//		groupBetImportNew.setImportedOn(System.currentTimeMillis());
//		groupGlobalBetImportRepo.save(groupBetImportNew);
//
//		response.info("Success: Added Bet to the Group") ;
//		return true ;
//	}
//
//	public boolean listImportedBets(WSResponse response, String userId, String groupId) {
//
//		List<GroupBetImport> groupBetImportList = groupGlobalBetImportRepo.findByGroupId(groupId) ;
//		response.put(ResponseConstants.LIST_IMPORTED_BETS, groupBetImportList);
//		return true ;
//	}
}
