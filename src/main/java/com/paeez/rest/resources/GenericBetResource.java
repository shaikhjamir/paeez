package com.paeez.rest.resources;

import com.paeez.core.model.GenericBet;
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
    private String createdTime ;   // creation of this Bet this is not at realted to the actual Bet
    private String lastModifiedTime ; //
    private BetStatus status ; // can be active, closed
    private String createdBy ; // user who created this
    private String closingTime ; // This is the time till which Bets are to be accepted, after this elapses Bets should not be accepted
    // also the closingTime is used while displaying to the user, all Bets will be sorted based on closingTime
    private long resultTime ; // Ideal time when the results will be declared
    private Map<String, String> options ; // options displayed such as Virat, Marsh etc. This should never be null rather should have at least 2 options
    private String winningOption;
    private BetTypes betType;
    private Map<String, Long> betMeasureByOptions;

    public Map<String, Long> getBetMeasureByOptions() {
        return betMeasureByOptions;
    }

    public void setBetMeasureByOptions(Map<String, Long> betMeasureByOptions) {
        this.betMeasureByOptions = betMeasureByOptions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public long getResultTime() {
        return resultTime;
    }

    public void setResultTime(long resultTime) {
        this.resultTime = resultTime;
    }

    public Map<String, String> getOptions() {
        return options;
    }

    public void setOptions(Map<String, String> options) {
        this.options = options;
    }

    public String getWinningOption() {
        return winningOption;
    }

    public void setWinningOption(String winningOption) {
        this.winningOption = winningOption;
    }

    public BetTypes getBetType() {
        return betType;
    }

    public void setBetType(BetTypes betType) {
        this.betType = betType;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(String lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public GenericBet toGenericBet() throws Exception {
        GenericBet genericBet = new GenericBet();
        genericBet.setOptions(this.getOptions());
        genericBet.setBetMeasureByOptions(this.getBetMeasureByOptions());
        genericBet.setBetType(this.getBetType());
        genericBet.setCreatedBy(this.getCreatedBy());
        genericBet.setDescription(this.getDescription());

        // Convert String to Date
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateInString = this.getCreatedTime();
        Date date = sdf.parse(dateInString);
        genericBet.setCreatedTime(date);

        dateInString = this.getClosingTime();
        date = sdf.parse(dateInString);
        genericBet.setClosingTime(date);


        //genericBet.setResultTime(this.getResultTime());

        return genericBet;

    }
}
