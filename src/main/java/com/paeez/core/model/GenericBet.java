package com.paeez.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paeez.core.services.constants.BetStatus;
import com.paeez.core.services.constants.BetTypes;
import com.paeez.core.services.constants.BetOptions;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.Map;

/**
 * This is the GlobalBet which will be imported
 * @author shaikhjamir
 *
 */
public class GenericBet {

	@Id
	private String id;
	
	private String description ; // can be question like who will score max runs

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdTime ;   // creation of this Bet this is not at realted to the actual Bet
	private Date lastModifiedTime ; //
	private BetStatus status ; // can be active, closed
	private String createdBy ; // user who created this
	private Date closingTime ; // This is the time till which Bets are to be accepted, after this elapses Bets should not be accepted
	   						   // also the closingTime is used while displaying to the user, all Bets will be sorted based on closingTime
	private long resultTime ; // Ideal time when the results will be declared
	// TODO change the
	private Map<BetOptions, String> options ; // options displayed such as Virat, Marsh etc. This should never be null rather should have at least 2 options
    private BetOptions winningOption;
	private BetTypes betType;
	private Map<String, Long> betMeasureByOptions; // TODO Remove this

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

	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public BetStatus getStatus() {
		return status;
	}

	public void setStatus(BetStatus status) {
		this.status = status;
	}

	public Date getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(Date closingTime) {
		this.closingTime = closingTime;
	}

	public long getResultTime() {
		return resultTime;
	}
	public void setResultTime(long resultTime) {
		this.resultTime = resultTime;
	}

	public Map<BetOptions, String> getOptions() {
		return options;
	}

	public void setOptions(Map<BetOptions, String> options) {
		this.options = options;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public BetTypes getBetType() {
		return betType;
	}

	public void setBetType(BetTypes betType) {
		this.betType = betType;
	}

	public BetOptions getWinningOption() {
		return winningOption;
	}

	public void setWinningOption(BetOptions winningOption) {
		this.winningOption = winningOption;
	}

	public Map<String, Long> getBetMeasureByOptions() {
		return betMeasureByOptions;
	}

	public void setBetMeasureByOptions(Map<String, Long> betMeasureByOptions) {
		this.betMeasureByOptions = betMeasureByOptions;
	}

	@Override
	public String toString() {
		return "GenericBet{" +
				"id='" + id + '\'' +
				", description='" + description + '\'' +
				", createdTime=" + createdTime +
				", lastModifiedTime=" + lastModifiedTime +
				", status='" + status + '\'' +
				", createdBy='" + createdBy + '\'' +
				", closingTime=" + closingTime +
				", resultTime=" + resultTime +
				", options=" + options +
				'}';
	}
}
