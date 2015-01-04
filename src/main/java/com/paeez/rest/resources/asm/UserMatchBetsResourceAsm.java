package com.paeez.rest.resources.asm;

import com.paeez.core.model.UserMatchBets;
import com.paeez.rest.controllers.UserMatchBetsController;
import com.paeez.rest.resources.UserMatchBetsResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Shrikant on 1/3/15.
 */
public class UserMatchBetsResourceAsm extends ResourceAssemblerSupport<UserMatchBets, UserMatchBetsResource> {

    public UserMatchBetsResourceAsm() {
        super(UserMatchBetsController.class, UserMatchBetsResource.class);
    }

    public UserMatchBetsResource toResource(UserMatchBets userMatchBets) {
        UserMatchBetsResource resource = new UserMatchBetsResource();
        resource.setUserId(userMatchBets.getUserId());
        resource.setGroupId(userMatchBets.getGroupId());
        resource.setBetsCartId(userMatchBets.getBetsCartId());
        resource.setMatchId(userMatchBets.getMatchBetId());

        resource.setChoice(userMatchBets.getChoice());
        resource.setUserResult(userMatchBets.getUserResult());

        resource.add(linkTo(UserMatchBetsController.class).slash(userMatchBets.getId()).slash("usermatchbetsinfo").withSelfRel());
        return resource;

    }
}
