package com.paeez.rest.resources.asm;

import com.paeez.core.model.UserPlayedBets;
import com.paeez.rest.controllers.UserPlayedBetsController;
import com.paeez.rest.resources.UserPlayedBetsResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Shrikant on 1/3/15.
 */
public class UserPlayedBetsResourceAsm extends ResourceAssemblerSupport<UserPlayedBets, UserPlayedBetsResource> {

    public UserPlayedBetsResourceAsm() {
        super(UserPlayedBetsController.class, UserPlayedBetsResource.class);
    }

    public UserPlayedBetsResource toResource(UserPlayedBets userPlayedBets) {
        UserPlayedBetsResource resource = new UserPlayedBetsResource();
        resource.setUserId(userPlayedBets.getUserId());
        resource.setGroupId(userPlayedBets.getGroupId());
        resource.setBetsCartId(userPlayedBets.getBetsCartId());
        resource.setGenericBetId(userPlayedBets.getGenericBetId());

        resource.setChoice(userPlayedBets.getChoice());
        resource.setUserResult(userPlayedBets.getUserResult());

        resource.add(linkTo(UserPlayedBetsController.class).slash(userPlayedBets.getId()).slash("userbetsinfo").withSelfRel());
        return resource;

    }
}
