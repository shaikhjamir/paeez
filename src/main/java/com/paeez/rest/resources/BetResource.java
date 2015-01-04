package com.paeez.rest.resources;

import com.paeez.core.model.MatchBet;
import com.paeez.core.services.constants.BetTypes;
import org.springframework.hateoas.ResourceSupport;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Shrikant on 1/1/15.
 */
public abstract class BetResource extends ResourceSupport{
    public abstract BetTypes getBetType();

    public MatchBet toMatchBet() throws Exception {
        MatchBet matchBet = new MatchBet();
        MatchBetResource mbRes = (MatchBetResource)this;
        matchBet.setIconTeam(mbRes.getIconTeam());
        matchBet.setChallengerTeam(mbRes.getChallengerTeam());

        // Convert String to Date
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateInString = mbRes.getMatchDate();
        Date date = sdf.parse(dateInString);
        matchBet.setMatchDate(date);

        return matchBet;
    }
}
