package com.paeez.rest.resources.asm;

import com.paeez.rest.controllers.BetsCartController;
import com.paeez.core.model.BetsCart;
import com.paeez.rest.resources.BetsCartResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Shrikant on 1/1/15.
 */
public class BetsCartResourceAsm extends ResourceAssemblerSupport<BetsCart, BetsCartResource> {

    public BetsCartResourceAsm() {
        super(BetsCartController.class, BetsCartResource.class);
    }

    @Override
    public BetsCartResource toResource(BetsCart betsCart) {
        BetsCartResource resource = new BetsCartResource();
        //resource.setBetType(betsCart.getBetType());
        resource.setBets(betsCart.getBets());
        resource.add(linkTo(BetsCartController.class).slash(betsCart.getId()).withSelfRel());
        return resource;
        }
}
