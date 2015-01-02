package com.paeez.core.model;

import org.springframework.data.annotation.Id;

public class Group {

	@Id
	private String id;
	
	private String name ;
	private String description ;
	private long createdTime ;
	private long lastModifiedTime ;
	private String status ;
	
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
	public long getLastModifiedTime() {
		return lastModifiedTime;
	}
	public void setLastModifiedTime(long lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}
	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + ", description="
				+ description + ", createdTime=" + createdTime
				+ ", lastModifiedTime=" + lastModifiedTime + ", status="
				+ status + "]";
	}
}
