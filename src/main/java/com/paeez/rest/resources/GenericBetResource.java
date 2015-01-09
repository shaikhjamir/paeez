package com.paeez.rest.resources;

import com.paeez.core.model.GenericBet;
import com.paeez.core.services.constants.BetOptions;
import com.paeez.core.services.constants.BetStatus;
import com.paeez.core.services.constants.BetTypes;
import org.springframework.hateoas.ResourceSupport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Shrikant on 1/3/15.
 */
public class GenericBetResource extends ResourceSupport{

    private String description ; // can be question like who will score max runs

    private Date createdTime ;   // creation of this Bet this is not at realted to the actual Bet
    private Date lastModifiedTime ; //
    private BetStatus status ; // can be active, closed
    private String createdBy ; // user who created this
    private Date closingTime ; // This is the time till which Bets are to be accepted, after this elapses Bets should not be accepted
    // also the closingTime is used while displaying to the user, all Bets will be sorted based on closingTime

    private Map<BetOptions, String> options ; // options displayed such as Virat, Marsh etc. This should never be null rather should have at least 2 options
    private BetOptions winningOption;
    private BetTypes betType;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(Date closingTime) {
        this.closingTime = closingTime;
    }

    public Map<BetOptions, String> getOptions() {
        return options;
    }

    public void setOptions(Map<BetOptions, String> options) {
        this.options = options;
    }

    public BetOptions getWinningOption() {
        return winningOption;
    }

    public void setWinningOption(BetOptions winningOption) {
        this.winningOption = winningOption;
    }

    public BetTypes getBetType() {
        return betType;
    }

    public void setBetType(BetTypes betType) {
        this.betType = betType;
    }
}
