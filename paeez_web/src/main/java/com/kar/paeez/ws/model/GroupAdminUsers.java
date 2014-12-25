package com.kar.paeez.ws.model;

import org.springframework.data.annotation.Id;

public class GroupAdminUsers {

	@Id private String id;
	private String group ;
	private String userEmailAddress ;
	private long addedOn ;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
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
		return "GroupAdminUsers [id=" + id + ", group=" + group + ", userEmailAddress="
				+ userEmailAddress + ", addedOn=" + addedOn + "]";
	}
}
