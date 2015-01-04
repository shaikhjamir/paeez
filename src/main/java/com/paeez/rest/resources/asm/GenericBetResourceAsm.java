package com.paeez.rest.resources.asm;

import com.paeez.core.model.GenericBet;
import com.paeez.rest.controllers.GenericBetController;
import com.paeez.rest.controllers.MatchBetController;
import com.paeez.rest.resources.GenericBetResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.text.SimpleDateFormat;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Shrikant on 1/3/15.
 */
public class GenericBetResourceAsm extends ResourceAssemblerSupport<GenericBet, GenericBetResource> {

    public GenericBetResourceAsm() {
        super(GenericBetController.class, GenericBetResource.class);
    }

    public GenericBetResource toResource(GenericBet genericBet) {
        GenericBetResource genericBetResource = new GenericBetResource();
        genericBetResource.setBetMeasureByOptions(genericBet.getBetMeasureByOptions());
        genericBetResource.setBetType(genericBet.getBetType());
        genericBetResource.setCreatedBy(genericBet.getCreatedBy());
        genericBetResource.setDescription(genericBet.getDescription());
        genericBetResource.setOptions(genericBet.getOptions());
        genericBetResource.setStatus(genericBet.getStatus());

        // Convert Date to String
        SimpleDateFormat sdf = new SimpleDateFormat();
        String dateString = sdf.format(genericBet.getClosingTime());
        genericBetResource.setClosingTime(dateString);

        dateString = sdf.format(genericBet.getCreatedBy());
        genericBetResource.setCreatedTime(dateString);

        genericBetResource.add(linkTo(GenericBetController.class).slash(genericBet.getId()).slash("betinfo").withSelfRel());

        return genericBetResource;
    }

}
