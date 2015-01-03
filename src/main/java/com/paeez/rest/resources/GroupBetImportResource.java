package com.paeez.rest.resources;

import com.paeez.core.model.GroupBetImport;
import org.springframework.hateoas.ResourceSupport;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Shrikant on 1/2/15.
 */
public class GroupBetImportResource extends ResourceSupport {
    private String groupId ; // GroupId for which the bet was imported
    private String betsCartId;
    private String importedByUserEmailAddress; // AdminUser who imported
    private String importedOn ; // date when imported

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getBetsCartId() {
        return betsCartId;
    }

    public void setBetsCartId(String betsCartId) {
        this.betsCartId = betsCartId;
    }

    public String getImportedByUserEmailAddress() {
        return importedByUserEmailAddress;
    }

    public void setImportedByUserEmailAddress(String importedByUserEmailAddress) {
        this.importedByUserEmailAddress = importedByUserEmailAddress;
    }

    public String getImportedOn() {
        return importedOn;
    }

    public void setImportedOn(String importedOn) {
        this.importedOn = importedOn;
    }

    public GroupBetImport toGroupBetImport() throws Exception {
        GroupBetImport groupBetImport = new GroupBetImport();
        groupBetImport.setGroupId(this.getGroupId());
        groupBetImport.setBetsCartId(this.getBetsCartId());
        groupBetImport.setImportedByUserEmailAddress(this.getImportedByUserEmailAddress());

        // Convert String to Date
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateInString = this.getImportedOn();
        Date date = sdf.parse(dateInString);

        groupBetImport.setImportedOn(date);
        return groupBetImport;
    }
}
