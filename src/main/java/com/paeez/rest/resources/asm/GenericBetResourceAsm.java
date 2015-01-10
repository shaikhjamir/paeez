package com.paeez.rest.resources.asm;

import com.paeez.core.model.GenericBet;
import com.paeez.rest.controllers.GenericBetController;
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

    @Override
    public GenericBetResource toResource(GenericBet genericBet) {
        GenericBetResource genericBetResource = createResourceWithId(genericBet.getId(), genericBet);
        return genericBetResource;
    }

    @Override
    public GenericBetResource instantiateResource(GenericBet genericBet) {
        return new GenericBetResource(genericBet);
    }
}
