package com.paeez.rest.resources;

import com.paeez.core.model.UserMatchBets;
import com.paeez.core.services.constants.BetWinner;
import com.paeez.core.services.constants.UserResult;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by Shrikant on 1/3/15.
 */
public class UserMatchBetsResource extends ResourceSupport {

    private String groupId;
    private String betsCartId;
    private String matchBetId;
    private BetWinner choice;
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

    public String getMatchBetId() {
        return matchBetId;
    }

    public void setMatchId(String matchId) {
        this.matchBetId = matchId;
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

    public UserMatchBets toUserMatchBets() {
        UserMatchBets userMatchBets = new UserMatchBets();
        userMatchBets.setGroupId(this.getGroupId());
        userMatchBets.setBetsCartId(this.getBetsCartId());
        userMatchBets.setMatchBetId(this.getMatchBetId());
        userMatchBets.setUserId(this.getUserId());

        //userMatchBets.setChoice(this.getChoice());

        return userMatchBets;
    }
}
