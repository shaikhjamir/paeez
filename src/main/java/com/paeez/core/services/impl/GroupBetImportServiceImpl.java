package com.paeez.core.services.impl;

import com.paeez.core.model.*;
import com.paeez.core.repositories.mongo.GroupBetImportRepository;
import com.paeez.core.services.api.GroupBetImportService;
import com.paeez.core.services.util.InputValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GroupBetImportServiceImpl extends BaseService implements GroupBetImportService {

//	private GroupBetImportRepository groupBetImportRepository;
//
//	@Autowired
//	public GroupBetImportServiceImpl(GroupBetImportRepository groupBetImportRepository) {
//		this.groupBetImportRepository = groupBetImportRepository;
//	}
	@Override
	public GroupBetImport findByGroupIdAndBetsCartId(String groupId, String betsCartId) {
		InputValidations.validateInputIdForNull("Either groupId or betsCartId is null/empty in" +
				"GroupBetImportService.findByGroupIdAndBetsCartId");

		InputValidations.validateGroupExists(groupId, "Group with id " + groupId + " does not exists");
		InputValidations.validateBetsCartExists(betsCartId, "BetsCart with id " + betsCartId + " does not exists");

		GroupBetImport groupBetImport = groupBetImportRepository.findByGroupIdAndBetsCartId(groupId, betsCartId);

		InputValidations.checkGroupBetImportFound(groupBetImport, "GroupBetImport entry not found for groupId "
					+ groupId + " and betsCartId" + betsCartId );

		return groupBetImport;
	}

	@Override
	public GroupBetImport findByGroupId(String groupId) {

		InputValidations.validateGroupExists(groupId, "Group with id " + groupId + " does not exists");
		GroupBetImport groupBetImport = groupBetImportRepository.findByGroupId(groupId);

		InputValidations.checkGroupBetImportFound(groupBetImport, "GroupBetImport not found for groupId " +
				groupId);

		return groupBetImport;
	}

	@Override
	public void update(GroupBetImport groupBetImport) {
        //GroupId and BetsCartId are required and should be valid
		InputValidations.validateInputIdForNull("Either groupId or betsCartId is null/empty in" +
				"GroupBetImportService.update", groupBetImport.getGroupId(), groupBetImport.getBetsCartId());

		InputValidations.validateGroupExists(groupBetImport.getGroupId(), "Group with id " + groupBetImport.getGroupId()
				+ " does not exists");
		InputValidations.validateBetsCartExists(groupBetImport.getBetsCartId(), "BetsCart with id "
				+ groupBetImport.getBetsCartId() + " does not exists");

		groupBetImportRepository.save(groupBetImport);
	}
	@Override
	public List<GroupBetImport> findByAll() {
		List<GroupBetImport> groupBetImports = groupBetImportRepository.findAll();
		InputValidations.checkGroupBetsImportFound(groupBetImports, "No GroupBetImports found");
		return groupBetImports;
	}
}
