package com.paeez.rest.controllers;

import com.paeez.core.model.UserPlayedBets;
import com.paeez.core.services.api.UserPlayedBetsService;
import com.paeez.rest.resources.UserPlayedBetsResource;
import com.paeez.rest.resources.asm.UserPlayedBetsResourceAsm;
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
@RequestMapping("/userbets")
public class UserPlayedBetsController {

    private UserPlayedBetsService userPlayedBetsService;

    @Autowired
    public UserPlayedBetsController(UserPlayedBetsService userPlayedBetsService) {
        this.userPlayedBetsService = userPlayedBetsService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserPlayedBetsResource>> findAll()
    {
        List<UserPlayedBets> userPlayedBets = userPlayedBetsService.findAll();
        List<UserPlayedBetsResource> userMatchBetsRes = new ArrayList<UserPlayedBetsResource>();
        for (UserPlayedBets userMatchBet : userPlayedBets) {
            userMatchBetsRes.add(new UserPlayedBetsResourceAsm().toResource(userMatchBet));
        }
        return new ResponseEntity<List<UserPlayedBetsResource>>(userMatchBetsRes, HttpStatus.OK);
    }

    @RequestMapping(value="/entry",
            method = RequestMethod.POST)
    public ResponseEntity<UserPlayedBetsResource> createBetEntry(
            @RequestBody UserPlayedBetsResource sentBet) throws Exception {

        UserPlayedBets createdMatchBet = sentBet.toUserPlayedBets();
        userPlayedBetsService.putBet(createdMatchBet);
        UserPlayedBetsResource createdResource = new UserPlayedBetsResourceAsm().toResource(createdMatchBet);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(createdResource.getLink("self").getHref()));
        return new ResponseEntity<UserPlayedBetsResource>(createdResource, headers, HttpStatus.CREATED);
    }
}
