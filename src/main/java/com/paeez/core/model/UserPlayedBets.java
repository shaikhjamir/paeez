package com.paeez.core.model;

import com.paeez.core.services.constants.BetWinner;
import com.paeez.core.services.constants.UserResult;
import org.springframework.data.annotation.Id;

/**
 * Created by Shrikant on 1/3/15.
 */
public class UserPlayedBets {
    @Id
    private String Id;
    private String groupId;
    private String betsCartId;
    private String genericBetId;
    private BetWinner choice;
    private UserResult userResult;
    private String userId;

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

    public BetWinner getChoice() {
        return choice;
    }

    public void setChoice(BetWinner choice) {
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
