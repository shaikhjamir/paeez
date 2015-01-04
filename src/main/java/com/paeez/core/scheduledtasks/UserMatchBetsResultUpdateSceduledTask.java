package com.paeez.core.scheduledtasks;

import com.paeez.core.model.MatchBet;
import com.paeez.core.model.UserMatchBets;
import com.paeez.core.services.api.MatchBetService;
import com.paeez.core.services.api.UserMatchBetsService;
import com.paeez.core.services.constants.UserResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Shrikant on 1/3/15.
 */
@Component
public class UserMatchBetsResultUpdateSceduledTask {

    @Autowired
    private UserMatchBetsService userMatchBetsService;

    @Autowired
    private MatchBetService matchBetService;

    @Scheduled(fixedRate = 5000)
    public void updateUserMatchBetsResult() {
        List<MatchBet> closedMatchBets = matchBetService.findClosed();
        for (MatchBet matchBet : closedMatchBets) {
            //UserMatchBets userMatchBets = userMatchBetsServices.findByMatchBetId(matchBet.getId());
            List<UserMatchBets> userMatchBets = userMatchBetsService.findByMatchBetIdAndResultsUpdateRequired(matchBet.getId());
            for (UserMatchBets userMatchBet : userMatchBets) {
                if (userMatchBets != null && matchBet.getBetWinner() != null && userMatchBet.getChoice() != null) {
                    if (matchBet.getBetWinner().equals(userMatchBet.getChoice()))
                        userMatchBet.setUserResult(UserResult.WON);
                    else
                        userMatchBet.setUserResult(UserResult.LOST);

                    userMatchBetsService.updateResult(userMatchBet);
                }
            }
        }
    }
}
