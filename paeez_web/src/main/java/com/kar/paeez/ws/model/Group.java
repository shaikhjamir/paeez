package com.kar.paeez.ws.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Group {

	@Id private String id;
	
	private String name ;
	private String description ;
	private long createdTime ;
	private String status ;
	
	// @DBRef List<User> users = new ArrayList<>() ;
	// @DBRef List<User> adminUsers = new ArrayList<>() ;

	@DBRef List<User> users ;
	@DBRef List<User> adminUsers ;

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<User> getAdminUsers() {
		return adminUsers;
	}
	public void setAdminUsers(List<User> adminUsers) {
		this.adminUsers = adminUsers;
	}
	
	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + ", description="
				+ description + ", createdTime=" + createdTime + ", status="
				+ status + ", users="  + ", adminUsers=" 
				+ "]";
	}
}
