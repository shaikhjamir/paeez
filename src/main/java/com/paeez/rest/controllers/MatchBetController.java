package com.paeez.rest.controllers;


import com.paeez.core.model.MatchBet;
import com.paeez.rest.resources.MatchBetResource;
import com.paeez.rest.resources.asm.MatchBetResourceAsm;
import com.paeez.core.services.api.MatchBetService;
import com.paeez.core.services.constants.BetStatus;
import com.paeez.core.services.constants.BetWinner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Shrikant on 12/21/14.
 */

@RestController
@RequestMapping("/matchbets")
public class MatchBetController {
    private MatchBetService betService;

    @Autowired
    public MatchBetController(MatchBetService betService) {
        this.betService = betService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<MatchBetResource>> findAllBets()
    {
        List<MatchBet> matchBets = betService.findAll();
        List<MatchBetResource> matchBetsRes = new ArrayList<MatchBetResource>();
        for (MatchBet matchBet : matchBets ) {
            matchBetsRes.add(new MatchBetResourceAsm().toResource(matchBet));
        }
        return new ResponseEntity<List<MatchBetResource>>(matchBetsRes, HttpStatus.OK);
    }
    @RequestMapping(value = "/active", method = RequestMethod.GET)
    public ResponseEntity<List<MatchBetResource>> findActive()
    {
        List<MatchBet> matchBets = betService.findActive();
        List<MatchBetResource> matchBetsRes = new ArrayList<MatchBetResource>();
        for (MatchBet matchBet : matchBets ) {
            matchBetsRes.add(new MatchBetResourceAsm().toResource(matchBet));
        }
        return new ResponseEntity<List<MatchBetResource>>(matchBetsRes, HttpStatus.OK);
    }
    @RequestMapping(value="/{betId}/betinfo",
            method = RequestMethod.GET)
    public ResponseEntity<MatchBetResource> getBet(@PathVariable String betId)
    {
        MatchBet matchBet = betService.findById(betId);
        if(matchBet != null) {
            MatchBetResource res = new MatchBetResourceAsm().toResource(matchBet);
            return new ResponseEntity<MatchBetResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<MatchBetResource>(HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value="/matchbet-entry",
            method = RequestMethod.POST)
    public ResponseEntity<MatchBetResource> createBetEntry(
            @RequestBody MatchBetResource sentBet) throws Exception {

        MatchBet createdMatchBet = sentBet.toMatchBet();
        betService.enterBet(createdMatchBet);
        MatchBetResource createdResource = new MatchBetResourceAsm().toResource(createdMatchBet);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(createdResource.getLink("self").getHref()));
        return new ResponseEntity<MatchBetResource>(createdResource, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{betId}/{winner}",
            method = RequestMethod.POST)
    public ResponseEntity<MatchBetResource> updateWinner(@PathVariable String betId, @PathVariable String winner)
            throws Exception {
        BetWinner bw = BetWinner.fromStringId(winner);
        if (bw != null) {
            betService.updateResult(betId, bw);
            betService.updateStatus(betId, BetStatus.CLOSED);
        }

        MatchBet updatedMatchBet = betService.findById(betId);
        MatchBetResource updatedResource = new MatchBetResourceAsm().toResource(updatedMatchBet);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(updatedResource.getLink("self").getHref()));
        return new ResponseEntity<MatchBetResource>(updatedResource, headers, HttpStatus.CREATED);
    }
}
