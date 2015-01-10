package com.paeez.core.model;

import com.paeez.core.services.constants.BetOptions;
import com.paeez.core.services.constants.UserResult;
import org.springframework.data.annotation.Id;

import java.util.Map;

/**
 * Created by Shrikant on 1/3/15.
 */
public class UserBets {

    /**
     * Below the fields
     *  theoritically
     *    GroupId+BetsCartId+GenericBetId+Choice is uniq
     *    The above combination is uniq
     *    The User can bet on another option
     *    So basically when we insert this we need to add a duplicate check and if if exists then we need tp update that with the latest value
     *    The User can also bet on other GenericId etc
     */
    @Id
    private String Id;
    private String groupId;
    private String groupBetsId;
    private String genericBetId;
    private BetOptions choice;
    private UserResult userResult;
    private String userId;
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

    public String getGroupBetsId() {
        return groupBetsId;
    }

    public void setGroupBetsId(String groupBetsId) {
        this.groupBetsId = groupBetsId;
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

    @Override
    public String toString() {
        return "UserBets{" +
                "Id='" + Id + '\'' +
                ", groupId='" + groupId + '\'' +
                ", groupBetsId='" + groupBetsId + '\'' +
                ", genericBetId='" + genericBetId + '\'' +
                ", choice=" + choice +
                ", userResult=" + userResult +
                ", userId='" + userId + '\'' +
                ", betMeasureByOptions=" + betMeasureByOptions +
                '}';
    }
}
