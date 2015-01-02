package com.paeez.core.model;

import org.springframework.data.annotation.Id;

public class GroupUsers {

	@Id
	private String id;
	private String groupId ;
	private String userEmailAddress ;
	private long addedOn ;
	
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
	public String getUserEmailAddress() {
		return userEmailAddress;
	}
	public void setUserEmailAddress(String userEmailAddress) {
		this.userEmailAddress = userEmailAddress;
	}
	
	public long getAddedOn() {
		return addedOn;
	}
	public void setAddedOn(long addedOn) {
		this.addedOn = addedOn;
	}
	@Override
	public String toString() {
		return "GroupUsers [id=" + id + ", groupId=" + groupId + ", userEmailAddress="
				+ userEmailAddress + ", addedOn=" + addedOn + "]";
	}
}
