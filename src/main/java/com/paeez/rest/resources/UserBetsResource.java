package com.paeez.rest.resources;

import com.paeez.core.model.UserBets;
import com.paeez.core.services.constants.BetOptions;
import com.paeez.core.services.constants.UserResult;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by Shrikant on 1/3/15.
 */
public class UserBetsResource extends ResourceSupport {

    private String groupId;
    private String betsCartId;
    private String genericBetId;
    private BetOptions choice;
    private UserResult userResult;
    private String userId;

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

}
