package com.paeez.rest.controllers;

import com.paeez.core.model.UserMatchBets;
import com.paeez.core.services.api.UserMatchBetsService;
import com.paeez.rest.resources.UserMatchBetsResource;
import com.paeez.rest.resources.asm.UserMatchBetsResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpHeaders;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shrikant on 1/3/15.
 */

@RestController
@RequestMapping("/usermatchbets")
public class UserMatchBetsController {

    private UserMatchBetsService userMatchBetsService;

    @Autowired
    public UserMatchBetsController(UserMatchBetsService userMatchBetsService) {
        this.userMatchBetsService = userMatchBetsService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserMatchBetsResource>> findAll()
    {
        List<UserMatchBets> userMatchBets = userMatchBetsService.findAll();
        List<UserMatchBetsResource> userMatchBetsRes = new ArrayList<UserMatchBetsResource>();
        for (UserMatchBets userMatchBet : userMatchBets ) {
            userMatchBetsRes.add(new UserMatchBetsResourceAsm().toResource(userMatchBet));
        }
        return new ResponseEntity<List<UserMatchBetsResource>>(userMatchBetsRes, HttpStatus.OK);
    }

    @RequestMapping(value="/entry",
            method = RequestMethod.POST)
    public ResponseEntity<UserMatchBetsResource> createBetEntry(
            @RequestBody UserMatchBetsResource sentBet) throws Exception {

        UserMatchBets createdMatchBet = sentBet.toUserMatchBets();
        userMatchBetsService.putBet(createdMatchBet);
        UserMatchBetsResource createdResource = new UserMatchBetsResourceAsm().toResource(createdMatchBet);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(createdResource.getLink("self").getHref()));
        return new ResponseEntity<UserMatchBetsResource>(createdResource, headers, HttpStatus.CREATED);
    }
}
