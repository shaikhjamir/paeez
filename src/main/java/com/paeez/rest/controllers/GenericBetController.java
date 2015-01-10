package com.paeez.rest.controllers;

import com.paeez.core.model.GenericBet;
import com.paeez.core.services.api.GenericBetService;
import com.paeez.core.services.constants.BetStatus;
import com.paeez.core.services.constants.BetOptions;
import com.paeez.rest.resources.GenericBetResource;
import com.paeez.rest.resources.asm.GenericBetResourceAsm;
import com.paeez.rest.validators.GenericBetValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shrikant on 1/3/15.
 */
@RestController
@RequestMapping("/bets")
public class GenericBetController {
    private GenericBetService genericBetService;

    @Autowired
    public GenericBetController(GenericBetService genericBetService) {
        this.genericBetService = genericBetService;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new GenericBetValidator());
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<GenericBetResource>> findAllBets()
    {
        List<GenericBet> genericBets = genericBetService.findAll();
        
        List<GenericBetResource> genericBetsRes = new ArrayList<GenericBetResource>();
        for (GenericBet genericBet : genericBets ) {
            genericBetsRes.add(new GenericBetResourceAsm().toResource(genericBet));
        }
        return new ResponseEntity<List<GenericBetResource>>(genericBetsRes, HttpStatus.OK);
    }
    @RequestMapping(value = "/active", method = RequestMethod.GET)
    public ResponseEntity<List<GenericBetResource>> findActive()
    {
        List<GenericBet> genericBets = genericBetService.findActive();
        List<GenericBetResource> genericBetsRes = new ArrayList<GenericBetResource>();
        for (GenericBet genericBet : genericBets ) {
            genericBetsRes.add(new GenericBetResourceAsm().toResource(genericBet));
        }
        return new ResponseEntity<List<GenericBetResource>>(genericBetsRes, HttpStatus.OK);
    }
    @RequestMapping(value="/{genericBetId}",
            method = RequestMethod.GET)
    public ResponseEntity<GenericBetResource> getBet(@PathVariable(value="genericBetId") String genericBetId)
    {
        GenericBet genericBet = genericBetService.findById(genericBetId);
        GenericBetResource res = new GenericBetResourceAsm().toResource(genericBet);
        return new ResponseEntity<GenericBetResource>(res, HttpStatus.OK);
     }
    @RequestMapping(value="/bet-entry",
            method = RequestMethod.PUT)
    public ResponseEntity<GenericBetResource> createBetEntry(
            @Valid @RequestBody GenericBet sentBet) throws Exception {

        GenericBet createdGenericBet = genericBetService.saveBet(sentBet);
        GenericBetResource createdResource = new GenericBetResourceAsm().toResource(createdGenericBet);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(createdResource.getLink("self").getHref()));
        return new ResponseEntity<GenericBetResource>(createdResource, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{betId}/{winnerOption}",
            method = RequestMethod.POST)
    public ResponseEntity<GenericBetResource> updateWinner(@PathVariable String betId, @PathVariable String winnerOption) {

        BetOptions bw = BetOptions.fromStringId(winnerOption);
        GenericBet updatedGenericBet = genericBetService.updateResult(betId, bw);
        updatedGenericBet = genericBetService.updateStatus(betId, BetStatus.CLOSED);

        GenericBetResource updatedResource = new GenericBetResourceAsm().toResource(updatedGenericBet);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(updatedResource.getLink("self").getHref()));
        return new ResponseEntity<GenericBetResource>(updatedResource, headers, HttpStatus.OK);
    }

}
