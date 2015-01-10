package com.paeez.rest.controllers;

import com.paeez.core.model.GroupBets;
import com.paeez.core.model.GenericBet;
import com.paeez.core.services.api.GenericBetService;
import com.paeez.core.services.exceptions.GroupBetsDoesNotExistsException;
import com.paeez.core.services.util.InputValidations;
import com.paeez.rest.resources.GroupBetsResource;
import com.paeez.rest.resources.asm.GroupBetsResourceAsm;
import com.paeez.core.services.api.GroupBetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Shrikant on 1/1/15.
 */

@RestController
@RequestMapping("/groupbets")
public class GroupBetsController {

    private GroupBetsService groupBetsService;

    @Autowired
    private GenericBetService genericBetService;

    @Autowired
    public GroupBetsController(GroupBetsService groupBetsService) {
        this.groupBetsService = groupBetsService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<GroupBetsResource>> findAll()
    {
        List<GroupBets> groupBets = groupBetsService.findAll();
        if (groupBets == null || groupBets.size() == 0)
            throw new GroupBetsDoesNotExistsException("No groupBets are available in store");

        List<GroupBetsResource> betsCartRes = new ArrayList<GroupBetsResource>();
        for (GroupBets groupBet : groupBets) {
            betsCartRes.add(new GroupBetsResourceAsm().toResource(groupBet));
        }
        return new ResponseEntity<List<GroupBetsResource>>(betsCartRes, HttpStatus.OK);
    }

    @RequestMapping(value="/{groupbetsId}", method = RequestMethod.GET)
    public ResponseEntity<GroupBetsResource> findOne(@PathVariable(value="groupbetsId") String groupbetsId) {
        GroupBets groupBets = groupBetsService.findById(groupbetsId);

        GroupBetsResource groupBetsRes = new GroupBetsResourceAsm().toResource(groupBets);
        return new ResponseEntity<GroupBetsResource>(groupBetsRes, HttpStatus.OK);
    }

    @RequestMapping(value = "/{groupbetsId}/genericbet/add", method = RequestMethod.POST)
    public ResponseEntity<GroupBetsResource> addGenericBetToCart(@PathVariable(value="groupbetsId") String groupbetsId, @RequestBody String genericBetId) {

        GroupBets updatedGroupBets = null;
        GenericBet genericBetInstance = null;
        GroupBetsResource updatedGroupBetsResource = null;

        InputValidations.validateForNull(genericBetId, "Invalid input");

        genericBetInstance = genericBetService.findById(genericBetId);

        updatedGroupBets = groupBetsService.addBetToCart(groupbetsId, genericBetInstance);

        updatedGroupBetsResource = new GroupBetsResourceAsm().toResource(updatedGroupBets);
        updatedGroupBetsResource.add(linkTo(GenericBetController.class).slash(genericBetInstance.getId()).withRel("genericbet"));

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(updatedGroupBetsResource.getLink("self").getHref()));
        return new ResponseEntity<GroupBetsResource>(updatedGroupBetsResource, headers, HttpStatus.OK);
    }

    @RequestMapping(value="/create",
            method = RequestMethod.POST)
    public ResponseEntity<GroupBetsResource> createBetsCartEntry(
            @RequestBody GroupBets groupBets) throws Exception {

        groupBets = groupBetsService.save(groupBets);
        GroupBetsResource createdResource = new GroupBetsResourceAsm().toResource(groupBets);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(createdResource.getLink("self").getHref()));
        return new ResponseEntity<GroupBetsResource>(createdResource, headers, HttpStatus.CREATED);
    }
}
