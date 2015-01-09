package com.paeez.core.scheduledtasks;

import com.paeez.core.model.GenericBet;
import com.paeez.core.model.UserPlayedBets;
import com.paeez.core.services.api.GenericBetService;
import com.paeez.core.services.api.UserPlayedBetsService;
import com.paeez.core.services.constants.UserResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Shrikant on 1/3/15.
 */
@Component
public class UserPlayedBetsResultUpdateSceduledTask {

    @Autowired
    private UserPlayedBetsService userPlayedBetsService;

    @Autowired
    private GenericBetService genericBetService;

    //@Scheduled(fixedRate = 5000)
    public void updateUserPlayedBetsResult() {
        List<GenericBet> closedGenericBets = genericBetService.findClosed();
        for (GenericBet genericBet : closedGenericBets) {
            //UserMatchBets userMatchBets = userMatchBetsServices.findByMatchBetId(matchBet.getId());
            List<UserPlayedBets> userPlayedBets = userPlayedBetsService.findByGenericBetIdAndUserResult(genericBet.getId());
            for (UserPlayedBets userPlayedBet : userPlayedBets) {
                if (userPlayedBets != null && genericBet.getWinningOption() != null && userPlayedBet.getChoice() != null) {
                    if (genericBet.getWinningOption().equals(userPlayedBet.getChoice()))
                        userPlayedBet.setUserResult(UserResult.WON);
                    else
                        userPlayedBet.setUserResult(UserResult.LOST);

                    userPlayedBetsService.updateResult(userPlayedBet);
                }
            }
        }
    }
}
