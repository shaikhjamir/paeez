package com.paeez.core.model;

import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

/**
 * Created by Shrikant on 1/1/15.
 */
public class GroupBets {

    @Id
    private String id;
    private String groupId;
    private List<String> genericBetIds;
    private String importedByUserEmailAddress; // AdminUser who imported
    private Date importedOn ; // date when imported

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "BetsCart{" +
                "id='" + id + '\'' +
                ", groupId='" + groupId + '\'' +
                ", genericBetId=" + genericBetIds +
                ", importedByUserEmailAddress='" + importedByUserEmailAddress + '\'' +
                ", importedOn=" + importedOn +
                '}';
    }
}
