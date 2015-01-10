package com.paeez.rest.resources.asm;

import com.paeez.rest.controllers.GroupBetsController;
import com.paeez.core.model.GroupBets;
import com.paeez.rest.resources.GroupBetsResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Shrikant on 1/1/15.
 */
public class GroupBetsResourceAsm extends ResourceAssemblerSupport<GroupBets, GroupBetsResource> {

    public GroupBetsResourceAsm() {
        super(GroupBetsController.class, GroupBetsResource.class);
    }

    @Override
    public GroupBetsResource toResource(GroupBets groupBets) {
        GroupBetsResource resource = createResourceWithId(groupBets.getId(), groupBets);
        return resource;
    }

    @Override
    public GroupBetsResource instantiateResource(GroupBets groupBets) {
        return new GroupBetsResource(groupBets);
    }
}
