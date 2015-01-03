package com.paeez.core.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;

/**
 * This is the GlobalBet which will be imported
 * @author shaikhjamir
 *
 */
public class GroupBetImport {

	@Id
	private String id;
	private String groupId ; // GroupId for which the bet was imported
	private String betsCartId;
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
	public Date getImportedOn() {
		return importedOn;
	}
	public void setImportedOn(Date importedOn) {
		this.importedOn = importedOn;
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

	@Override
	public String toString() {
		return "GroupBetImport{" +
				"id='" + id + '\'' +
				", groupId='" + groupId + '\'' +
				", betsCartId='" + betsCartId + '\'' +
				", importedByUserEmailAddress='" + importedByUserEmailAddress + '\'' +
				", importedOn=" + importedOn +
				'}';
	}
}
