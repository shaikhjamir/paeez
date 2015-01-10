package com.paeez.core.model;

import com.paeez.core.services.constants.BetOptions;
import com.paeez.core.services.constants.UserResult;

import org.springframework.data.annotation.Id;

import java.util.Map;

import javax.validation.constraints.NotNull;

/**
 * Created by Shrikant on 1/3/15.
 */
public class UserBets {

    /**
     * Below the fields
     *  ideally
     *    userId+GroupId+GenericBetId is uniq
     *    The above combination is uniq
     *    The User can bet on another option so its a Map-betMeasureByOptions
     *    The User can also bet on other GenericId etc
     */
    @Id
    private String Id;
    
    @NotNull
    private String groupId;
    
    @NotNull
    private String genericBetId;
    
    private UserResult userResult;
    
    @NotNull
    private String userId;
    
    // This can be null in case of withdrawal from Bet
    private Map<BetOptions, Long> betMeasureByOptions;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    
    public Map<BetOptions, Long> getBetMeasureByOptions() {
        return betMeasureByOptions;
    }

    public void setBetMeasureByOptions(Map<BetOptions, Long> betMeasureByOptions) {
        this.betMeasureByOptions = betMeasureByOptions;
    }

    public String getGenericBetId() {
        return genericBetId;
    }

    public void setGenericBetId(String genericBetId) {
        this.genericBetId = genericBetId;
    }

    public UserResult getUserResult() {
        return userResult;
    }

    public void setUserResult(UserResult userResult) {
        this.userResult = userResult;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

	@Override
	public String toString() {
		return "UserBets [Id=" + Id + ", groupId=" + groupId
				+ ", genericBetId=" + genericBetId + ", userResult="
				+ userResult + ", userId=" + userId + ", betMeasureByOptions="
				+ betMeasureByOptions + "]";
	}
}
