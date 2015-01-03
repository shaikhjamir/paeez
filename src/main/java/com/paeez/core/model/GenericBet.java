package com.paeez.core.model;

import org.springframework.data.annotation.Id;

import java.util.Arrays;

/**
 * This is the GlobalBet which will be imported
 * @author shaikhjamir
 *
 */
public class GenericBet {

	@Id
	private String id;
	
	private String description ; // can be question like who will score max runs
	private long createdTime ;   // creation of this Bet this is not at realted to the actual Bet
	private long lastModifiedTime ; //  
	private String status ; // can be active, closed
	private String createdBy ; // user who created this
	private long closingTime ; // This is the time till which Bets are to be accepted, after this elapses Bets should not be accepted
	   						   // also the closingTime is used while displaying to the user, all Bets will be sorted based on closingTime
	private long resultTime ; // Ideal time when the results will be declared
	private String[] options ; // options displayed such as Virat, Marsh etc. This should never be null rather should have at least 2 options
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String[] getOptions() {
		return options;
	}
	public void setOptions(String[] options) {
		this.options = options;
	}
	public void setOptions(String options) {
		// default separator ":"
		this.options = options.split(":");
	}
	public long getClosingTime() {
		return closingTime;
	}
	public void setClosingTime(long closingTime) {
		this.closingTime = closingTime;
	}
	public long getResultTime() {
		return resultTime;
	}
	public void setResultTime(long resultTime) {
		this.resultTime = resultTime;
	}
	
	@Override
	public String toString() {
		return "GlobalBet [id=" + id + ", description=" + description
				+ ", createdTime=" + createdTime + ", lastModifiedTime="
				+ lastModifiedTime + ", status=" + status + ", createdBy="
				+ createdBy + ", closingTime=" + closingTime + ", resultTime="
				+ resultTime + ", options=" + Arrays.toString(options) + "]";
	}
}
