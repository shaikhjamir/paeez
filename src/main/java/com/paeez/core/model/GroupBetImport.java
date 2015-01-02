package com.paeez.core.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * This is the GlobalBet which will be imported
 * @author shaikhjamir
 *
 */
public class GroupBetImport {

	@Id
	private String id;
	
	private String groupId ; // GroupId for which the bet was imported
	@DBRef
	GlobalBet globalBet ; // Reference Global Bet
	private String importedByUserEmailAddress; // AdminUser who imported 
	private long importedOn ; // date when imported
	
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
	public GlobalBet getGlobalBet() {
		return globalBet;
	}
	public void setGlobalBet(GlobalBet globalBet) {
		this.globalBet = globalBet;
	}
	public String getImportedByUserEmailAddres() {
		return importedByUserEmailAddress;
	}
	public void setImportedByUserEmailAddres(String importedByUserId) {
		this.importedByUserEmailAddress = importedByUserId;
	}
	public long getImportedOn() {
		return importedOn;
	}
	public void setImportedOn(long importedOn) {
		this.importedOn = importedOn;
	}
	
	@Override
	public String toString() {
		return "GroupBetImport [id=" + id + ", groupId=" + groupId
				+ ", globalBet=" + globalBet + ", importedByUserId="
				+ importedByUserEmailAddress + ", importedOn=" + importedOn + "]";
	}
	
}
