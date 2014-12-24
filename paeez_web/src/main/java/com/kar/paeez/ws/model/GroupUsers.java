package com.kar.paeez.ws.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class GroupUsers {

	@Id private String id;
	@DBRef Group group ;
	@DBRef User user ;
	private long addedOn ;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public long getAddedOn() {
		return addedOn;
	}
	public void setAddedOn(long addedOn) {
		this.addedOn = addedOn;
	}
	
	@Override
	public String toString() {
		return "GroupUsers [id=" + id + ", group=" + group + ", user=" + user
				+ ", addedOn=" + addedOn + "]";
	}
	
}
