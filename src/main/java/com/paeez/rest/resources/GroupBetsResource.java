package com.paeez.rest.resources;

import com.paeez.core.model.GroupBets;
import org.springframework.hateoas.ResourceSupport;

import java.util.Date;
import java.util.List;

/**
 * Created by Shrikant on 1/1/15.
 */
public class GroupBetsResource extends ResourceSupport {
    private String groupId;
    private List<String> genericBetIds;
    private String importedByUserEmailAddress; // AdminUser who imported
    private Date importedOn ; // date when imported

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<String> getGenericBetIds() {
        return genericBetIds;
    }

    public void setGenericBetIds(List<String> genericBetIds) {
        this.genericBetIds = genericBetIds;
    }

    public String getImportedByUserEmailAddress() {
        return importedByUserEmailAddress;
    }

    public void setImportedByUserEmailAddress(String importedByUserEmailAddress) {
        this.importedByUserEmailAddress = importedByUserEmailAddress;
    }

    public Date getImportedOn() {
        return importedOn;
    }

    public void setImportedOn(Date importedOn) {
        this.importedOn = importedOn;
    }

    public GroupBetsResource(GroupBets groupBets) {
        this.groupId = groupBets.getGroupId();
        this.genericBetIds = groupBets.getGenericBetIds();
        this.importedByUserEmailAddress = groupBets.getImportedByUserEmailAddress();
        this.importedOn = groupBets.getImportedOn();
    }
}

