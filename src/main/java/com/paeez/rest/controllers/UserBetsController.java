package com.paeez.rest.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.paeez.core.model.UserBets;
import com.paeez.core.services.api.UserBetsService;
import com.paeez.rest.resources.UserBetsResource;
import com.paeez.rest.resources.asm.UserBetsResourceAsm;
import com.paeez.rest.validators.UserBetsValidator;

/**
 * Created by Shrikant on 1/3/15.
 */

@RestController
@RequestMapping("/userbets")
public class UserBetsController {

	@Autowired
    private UserBetsService userBetsService;

    @Autowired
    private UserBetsValidator userBetsValidator;
    
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(userBetsValidator);
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

	/**
     * User Entering a Bet/Bets
     * i.e. user is putting his points on one or more options
     * @param userBet
     * @return
     * @throws Exception
Sample data for the call 
{
    "groupId": "group-id-grp-1",
    "genericBetId": "genericbetid-gen-bet",
    "userResult": null,
    "userId": "user-id-1",
    "betMeasureByOptions": {
        "OPTIONA": 11,
        "OPTIONB": 12,
        "OPTIONC": 13
    },
    "id": null
}

     */
    @RequestMapping(value="/putBet",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserBetsResource> putBet(
    		@Valid @RequestBody UserBets userBet) throws Exception {

    	userBet = userBetsService.putBet(userBet);
        UserBetsResource createdResource = new UserBetsResourceAsm().toResource(userBet);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(createdResource.getLink("self").getHref()));
        return new ResponseEntity<UserBetsResource>(createdResource, headers, HttpStatus.CREATED);
    }
}
