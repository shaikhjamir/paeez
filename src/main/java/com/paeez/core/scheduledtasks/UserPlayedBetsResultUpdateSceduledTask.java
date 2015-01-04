package com.paeez.core.scheduledtasks;

import com.paeez.core.model.GenericBet;
import com.paeez.core.model.MatchBet;
import com.paeez.core.model.UserPlayedBets;
import com.paeez.core.services.api.GenericBetService;
import com.paeez.core.services.api.MatchBetService;
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
    private MatchBetService matchBetService;

    @Autowired
    private GenericBetService genericBetService;

    @Scheduled(fixedRate = 5000)
    public void updateUserMatchBetsResult() {
        List<MatchBet> closedMatchBets = matchBetService.findClosed();
        for (MatchBet matchBet : closedMatchBets) {
            //UserMatchBets userMatchBets = userMatchBetsServices.findByMatchBetId(matchBet.getId());
            List<UserPlayedBets> userPlayedBets = userPlayedBetsService.findByGenericBetIdAndUserResult(matchBet.getId());
            for (UserPlayedBets userMatchBet : userPlayedBets) {
                if (userPlayedBets != null && matchBet.getBetWinner() != null && userMatchBet.getChoice() != null) {
                    if (matchBet.getBetWinner().equals(userMatchBet.getChoice()))
                        userMatchBet.setUserResult(UserResult.WON);
                    else
                        userMatchBet.setUserResult(UserResult.LOST);

                    userPlayedBetsService.updateResult(userMatchBet);
                }
            }
        }
    }
    @Scheduled(fixedRate = 5000)
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
