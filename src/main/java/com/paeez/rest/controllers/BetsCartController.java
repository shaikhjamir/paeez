package com.paeez.rest.controllers;

import com.paeez.core.model.BetsCart;
import com.paeez.core.model.GenericBet;
import com.paeez.core.model.MatchBet;
import com.paeez.core.services.api.GenericBetService;
import com.paeez.core.services.exceptions.BetsCartDoesNotExistsException;
import com.paeez.core.services.util.InputValidations;
import com.paeez.rest.resources.BetIdResource;
import com.paeez.rest.resources.BetsCartResource;
import com.paeez.rest.resources.asm.BetsCartResourceAsm;
import com.paeez.core.services.api.BetsCartService;
import com.paeez.core.services.api.MatchBetService;
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
@RequestMapping("/betscart")
public class BetsCartController {

    private BetsCartService betsCartService;
    private MatchBetService matchBetService;

    @Autowired
    private GenericBetService genericBetService;

    @Autowired
    public BetsCartController(BetsCartService betsCartService, MatchBetService matchBetService) {
        this.betsCartService = betsCartService;
        this.matchBetService = matchBetService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<BetsCartResource>> findAll()
    {
        List<BetsCart> betsCarts = betsCartService.findAll();
        if (betsCarts == null || betsCarts.size() == 0)
            throw new BetsCartDoesNotExistsException("No betscarts are available in store");

        List<BetsCartResource> betsCartRes = new ArrayList<BetsCartResource>();
        for (BetsCart betsCart : betsCarts ) {
            betsCartRes.add(new BetsCartResourceAsm().toResource(betsCart));
        }
        return new ResponseEntity<List<BetsCartResource>>(betsCartRes, HttpStatus.OK);
    }

    @RequestMapping(value = "/{betCartId}/genericbet/add", method = RequestMethod.POST)
    public ResponseEntity<BetsCartResource> addGenericBetToCart(@PathVariable(value="betCartId") String betCartId, @RequestBody BetIdResource betIdResource) {

        BetsCart updatedBetsCart = null;
        GenericBet genericBetInstance = null;
        BetsCartResource updatedBetsCartResource = null;

        InputValidations.validateForNull(betIdResource, "Invalid input");

        genericBetInstance = genericBetService.findById(betIdResource.getBetId());

        updatedBetsCart = betsCartService.addBetToCart(betCartId, genericBetInstance);

        updatedBetsCartResource = new BetsCartResourceAsm().toResource(updatedBetsCart);
        updatedBetsCartResource.add(linkTo(MatchBetController.class).slash(genericBetInstance.getId()).slash("betinfo").withRel("genericbet"));

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(updatedBetsCartResource.getLink("self").getHref()));
        return new ResponseEntity<BetsCartResource>(updatedBetsCartResource, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{betCartId}/matchbet/add", method = RequestMethod.POST)
    public ResponseEntity<BetsCartResource> addBetToCart(@PathVariable(value="betCartId") String betCartId, @RequestBody BetIdResource betIdResource) {

        BetsCart updatedBetsCart = null;
        MatchBet matchBetInstance = null;
        BetsCartResource updatedBetsCartResource = null;
        try {
            matchBetInstance = matchBetService.findById(betIdResource.getBetId());
            if (matchBetInstance != null) {
                updatedBetsCart = betsCartService.addBetToCart(betCartId, matchBetInstance);

                updatedBetsCartResource = new BetsCartResourceAsm().toResource(updatedBetsCart);
                updatedBetsCartResource.add(linkTo(MatchBetController.class).slash(matchBetInstance.getId()).slash("betinfo").withRel("matchbet"));
            }
        } catch (Exception e) {
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(updatedBetsCartResource.getLink("self").getHref()));
        return new ResponseEntity<BetsCartResource>(updatedBetsCartResource, headers, HttpStatus.OK);
    }

    @RequestMapping(value="/create",
            method = RequestMethod.POST)
    public ResponseEntity<BetsCartResource> createBetsCartEntry(
            @RequestBody BetsCartResource sentBetsCart) throws Exception {

        BetsCart createdBetsCart = sentBetsCart.toBetsCart();
        betsCartService.save(createdBetsCart);
        BetsCartResource createdResource = new BetsCartResourceAsm().toResource(createdBetsCart);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(createdResource.getLink("self").getHref()));
        return new ResponseEntity<BetsCartResource>(createdResource, headers, HttpStatus.CREATED);
    }
}
