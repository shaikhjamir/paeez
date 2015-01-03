package com.paeez.rest.resources.asm;

import com.paeez.core.model.GroupBetImport;
import com.paeez.rest.controllers.GroupBetImportController;
import com.paeez.rest.resources.GroupBetImportResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.text.SimpleDateFormat;

/**
 * Created by Shrikant on 1/2/15.
 */
public class GroupBetImportResourceAsm extends ResourceAssemblerSupport<GroupBetImport, GroupBetImportResource> {
    public GroupBetImportResourceAsm() {
        super(GroupBetImportController.class, GroupBetImportResource.class);
    }

    public GroupBetImportResource toResource(GroupBetImport groupBetImport){
        GroupBetImportResource groupBetImportResource = new GroupBetImportResource();
        groupBetImportResource.setBetsCartId(groupBetImport.getBetsCartId());
        groupBetImportResource.setGroupId(groupBetImport.getGroupId());
        groupBetImportResource.setImportedByUserEmailAddress(groupBetImport.getImportedByUserEmailAddress());

        // Convert Date to String
        SimpleDateFormat sdf = new SimpleDateFormat();
        String dateString = sdf.format(groupBetImport.getImportedOn());
        groupBetImportResource.setImportedOn(dateString);

        groupBetImportResource.add(linkTo(GroupBetImportController.class).slash(groupBetImport.getBetsCartId()).slash(groupBetImport.getGroupId())
                .slash("groupbetimportinfo").withSelfRel());

        return groupBetImportResource;
    }
}
