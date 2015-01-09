package com.paeez.core.model;

import com.paeez.core.services.constants.BetOptions;
import com.paeez.core.services.constants.UserResult;
import org.springframework.data.annotation.Id;

/**
 * Created by Shrikant on 1/3/15.
 */
public class UserPlayedBets {

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
    private String betsCartId;
    private String genericBetId;
    private BetOptions choice;
    private UserResult userResult;
    private String userId;

    // add the field private Map<String, Long> betMeasureByOptions; // TODO
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

    public String getBetsCartId() {
        return betsCartId;
    }

    public void setBetsCartId(String betsCartId) {
        this.betsCartId = betsCartId;
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
        return "UserMatchBets{" +
                "Id='" + Id + '\'' +
                ", groupId='" + groupId + '\'' +
                ", betsCartId='" + betsCartId + '\'' +
                ", genericBetId='" + genericBetId + '\'' +
                ", choice=" + choice +
                ", userResult=" + userResult +
                ", userId='" + userId + '\'' +
                '}';
    }
}
