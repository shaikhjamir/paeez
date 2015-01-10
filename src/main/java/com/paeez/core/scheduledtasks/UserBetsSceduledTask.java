package com.paeez.core.scheduledtasks;

import com.paeez.core.model.GenericBet;
import com.paeez.core.model.UserBets;
import com.paeez.core.services.api.GenericBetService;
import com.paeez.core.services.api.UserBetsService;
import com.paeez.core.services.constants.UserResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Shrikant on 1/3/15.
 */
@Component
public class UserBetsSceduledTask {

    @Autowired
    private UserBetsService userBetsService;

    @Autowired
    private GenericBetService genericBetService;

    //@Scheduled(fixedRate = 5000)
    public void updateUserPlayedBetsResult() {
        List<GenericBet> closedGenericBets = genericBetService.findClosed();
        for (GenericBet genericBet : closedGenericBets) {
            //UserMatchBets userMatchBets = userMatchBetsServices.findByMatchBetId(matchBet.getId());
            List<UserBets> userBets = userBetsService.findByGenericBetIdAndUserResult(genericBet.getId());
            for (UserBets userPlayedBet : userBets) {
            	
            	// TODO fix this since the user can select multiple options its not a single getChoice
            	// User can bet on multiple choices with different values 
            	/*
                if (userBets != null && genericBet.getWinningOption() != null && userPlayedBet.getChoice() != null) {
                    if (genericBet.getWinningOption().equals(userPlayedBet.getChoice()))
                        userPlayedBet.setUserResult(UserResult.WON);
                    else
                        userPlayedBet.setUserResult(UserResult.LOST);

                    userBetsService.updateResult(userPlayedBet);
                }
                */
            }
        }
    }
}
