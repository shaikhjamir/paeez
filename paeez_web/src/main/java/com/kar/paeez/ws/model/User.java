package com.kar.paeez.ws.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class User {

	@Id private String id;
	private String name;
	@Indexed(unique=true) private String emailAddress;

	private String status ;
	private long lastActivity ;
	private long lastLoggedIn ;
	private long createdTime ;
	
	@DBRef List<Group> groups ; 
	
	public String getName() {
		return name;
	}

	public void setName(String firstName) {
		this.name = firstName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getLastActivity() {
		return lastActivity;
	}

	public void setLastActivity(long lastActivity) {
		this.lastActivity = lastActivity;
	}

	public long getLastLoggedIn() {
		return lastLoggedIn;
	}

	public void setLastLoggedIn(long lastLoggedIn) {
		this.lastLoggedIn = lastLoggedIn;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", emailaddress="
				+ emailAddress + ", status=" + status + ", lastActivity="
				+ lastActivity + ", lastLoggedIn=" + lastLoggedIn
				+ ", createdTime=" + createdTime + ", groups="  + "]";
	}
	
}
