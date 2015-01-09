package com.paeez.rest.controllers;

import com.paeez.core.model.UserBets;
import com.paeez.core.services.api.UserBetsService;
import com.paeez.rest.resources.UserBetsResource;
import com.paeez.rest.resources.asm.UserBetsResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpHeaders;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shrikant on 1/3/15.
 */

@RestController
@RequestMapping("/userbets")
public class UserBetsController {

    private UserBetsService userBetsService;

    @Autowired
    public UserBetsController(UserBetsService userBetsService) {
        this.userBetsService = userBetsService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserBetsResource>> findAll()
    {
        List<UserBets> userBets = userBetsService.findAll();
        List<UserBetsResource> userBetsRes = new ArrayList<UserBetsResource>();
        for (UserBets userBet : userBets) {
            userBetsRes.add(new UserBetsResourceAsm().toResource(userBet));
        }
        return new ResponseEntity<List<UserBetsResource>>(userBetsRes, HttpStatus.OK);
    }

    @RequestMapping(value="/{userBetsId}", method = RequestMethod.GET)
    public ResponseEntity<UserBetsResource> findOne(@PathVariable(value="userBetsId") String userBetsId) {
        UserBets userBets = userBetsService.findById(userBetsId);
        UserBetsResource userBetsRes = new UserBetsResourceAsm().toResource(userBets);

        return new ResponseEntity<UserBetsResource>(userBetsRes, HttpStatus.OK);
    }

    @RequestMapping(value="/entry",
            method = RequestMethod.PUT)
    public ResponseEntity<UserBetsResource> createBetEntry(
            @RequestBody UserBets userBet) throws Exception {

        userBetsService.putBet(userBet);
        UserBetsResource createdResource = new UserBetsResourceAsm().toResource(userBet);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(createdResource.getLink("self").getHref()));
        return new ResponseEntity<UserBetsResource>(createdResource, headers, HttpStatus.CREATED);
    }
}
