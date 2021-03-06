package com.paeez.rest.resources.asm;

import com.paeez.core.model.UserBets;
import com.paeez.rest.controllers.UserBetsController;
import com.paeez.rest.resources.UserBetsResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Shrikant on 1/3/15.
 */
public class UserBetsResourceAsm extends ResourceAssemblerSupport<UserBets, UserBetsResource> {

    public UserBetsResourceAsm() {
        super(UserBetsController.class, UserBetsResource.class);
    }

    @Override
    public UserBetsResource toResource(UserBets userBets) {
        UserBetsResource resource = createResourceWithId(userBets.getId(), userBets);
        return resource;
    }

    @Override
    public UserBetsResource instantiateResource(UserBets userBets) {
        return new UserBetsResource(userBets);
    }
}
