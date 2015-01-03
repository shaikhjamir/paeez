package com.paeez.rest.controllers;

import com.paeez.core.services.api.GroupBetImportService;
import com.paeez.core.model.GroupBetImport;
import com.paeez.rest.resources.GroupBetImportResource;
import com.paeez.rest.resources.asm.GroupBetImportResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController("/groupbetimport")
public class GroupBetImportController {

	private GroupBetImportService groupBetImportService;

	@Autowired
	public GroupBetImportController(GroupBetImportService groupBetImportService) {
		this.groupBetImportService = groupBetImportService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<GroupBetImportResource>> findAll()
	{
		List<GroupBetImport> groupBetImports = groupBetImportService.findByAll();
		List<GroupBetImportResource> groupBetImportResources = new ArrayList<GroupBetImportResource>();
		for (GroupBetImport groupBetImport : groupBetImports ) {
			groupBetImportResources.add(new GroupBetImportResourceAsm().toResource(groupBetImport));
		}
		return new ResponseEntity<List<GroupBetImportResource>>(groupBetImportResources, HttpStatus.OK);
	}

	@RequestMapping(value="/update",
			method = RequestMethod.POST)
	public ResponseEntity<GroupBetImportResource> groupBetImportEntry(@RequestBody GroupBetImportResource
																				  sentGroupBetImportResource) throws Exception {

		GroupBetImport updatedGroupBetImport = sentGroupBetImportResource.toGroupBetImport();
		groupBetImportService.update(updatedGroupBetImport);
		GroupBetImportResource updatedResource = new GroupBetImportResourceAsm().toResource(updatedGroupBetImport);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create(updatedResource.getLink("self").getHref()));
		return new ResponseEntity<GroupBetImportResource>(updatedResource, headers, HttpStatus.CREATED);
	}

	@RequestMapping(value="/{groupId}/groupbetimportinfo",
			method = RequestMethod.GET)
	public ResponseEntity<GroupBetImportResource> getGroupBetImport(@PathVariable String groupId)
	{
		GroupBetImport groupBetImport = groupBetImportService.findByGroupId(groupId);
		if(groupBetImport != null) {
			GroupBetImportResource res = new GroupBetImportResourceAsm().toResource(groupBetImport);
			return new ResponseEntity<GroupBetImportResource>(res, HttpStatus.OK);
		} else {
			return new ResponseEntity<GroupBetImportResource>(HttpStatus.NOT_FOUND);
		}
	}

//	@Autowired
//	private GroupBetImportServiceImpl groupBetImportBO;
//
//	@RequestMapping(value="/grpBetImport/{userId}/{grpId}/import/{globalBetId}")
//    public List<String> importGlobalBet(@PathVariable("userId") String userId, @PathVariable("grpId") String grpId, @PathVariable("globalBetId") String globalBetId) {
//
//		WSResponse response = new WSResponse() ;
//		groupBetImportBO.importBet(response, userId, grpId, globalBetId) ;
//		return response.getMessages() ;
//    }
//
//	@RequestMapping(value="/grpBetImport/{userId}/{grpId}/list")
//    public List<GroupBetImport> listImportedBets(@PathVariable("userId") String userId, @PathVariable("grpId") String grpId) {
//
//		WSResponse response = new WSResponse() ;
//		groupBetImportBO.listImportedBets(response, userId, grpId) ;
//		return (List<GroupBetImport>)response.get(ResponseConstants.LIST_IMPORTED_BETS) ;
//    }

}
