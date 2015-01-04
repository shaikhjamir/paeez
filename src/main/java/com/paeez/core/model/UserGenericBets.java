package com.paeez.core.model;

import com.paeez.core.services.constants.BetWinner;
import com.paeez.core.services.constants.UserResult;
import org.springframework.data.annotation.Id;

/**
 * Created by Shrikant on 1/4/15.
 */
public class UserGenericBets {
    @Id
    private String Id;
    private String groupId;
    private String betsCartId;
    private String matchBetId;
    private String choice;
    private UserResult userResult;
    private String userId;
}
