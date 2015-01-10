package com.paeez.rest.resources;

import com.paeez.core.model.UserBets;
import com.paeez.core.services.constants.BetOptions;
import com.paeez.core.services.constants.UserResult;
import org.springframework.hateoas.ResourceSupport;

import java.util.Map;

/**
 * Created by Shrikant on 1/3/15.
 */
public class UserBetsResource extends ResourceSupport {

    private String groupId;
    private String groupBetsId;
    private String genericBetId;
    private BetOptions choice;
    private UserResult userResult;
    private String userId;
    private Map<BetOptions, Long> betMeasureByOptions;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupBetsId() {
        return groupBetsId;
    }

    public void setGroupBetsId(String groupBetsId) {
        this.groupBetsId = groupBetsId;
    }

    public String getGenericBetId() {
        return genericBetId;
    }

    public void setGenericBetId(String genericBetId) {
        this.genericBetId = genericBetId;
    }

    public BetOptions getChoice() {
        return choice;
    }

    public void setChoice(BetOptions choice) {
        this.choice = choice;
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

    public Map<BetOptions, Long> getBetMeasureByOptions() {
        return betMeasureByOptions;
    }

    public void setBetMeasureByOptions(Map<BetOptions, Long> betMeasureByOptions) {
        this.betMeasureByOptions = betMeasureByOptions;
    }

    public UserBetsResource(UserBets userBets) {
        this.groupId = userBets.getGroupId();
        this.groupBetsId = userBets.getGroupBetsId();
        this.genericBetId = userBets.getGenericBetId();
        this.userResult = userBets.getUserResult();
        this.userId = userBets.getUserId();
        this.betMeasureByOptions = userBets.getBetMeasureByOptions();
    }
}
