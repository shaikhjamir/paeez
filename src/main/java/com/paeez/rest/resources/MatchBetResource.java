package com.paeez.rest.resources;

import com.paeez.core.model.MatchBet;
import com.paeez.core.services.contants.BetStatus;
import com.paeez.core.services.contants.BetTypes;
import com.paeez.core.services.contants.BetWinner;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Shrikant on 12/21/14.
 */
public class MatchBetResource extends BetResource  {

    private String iconTeam;
    private String challengerTeam;
    private String matchDate;
    private BetTypes betType;
    private BetStatus status;
    private BetWinner betWinner;

    @Override
    public BetTypes getBetType() {
        return betType;
    }

    public void setBetType(BetTypes betType) {
        this.betType = betType;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public String getIconTeam() {
        return iconTeam;
    }

    public void setIconTeam(String iconTeam) {
        this.iconTeam = iconTeam;
    }

    public String getChallengerTeam() {
        return challengerTeam;
    }

    public void setChallengerTeam(String challengerTeam) {
        this.challengerTeam = challengerTeam;
    }

    public BetStatus getStatus() {
        return status;
    }

    public void setStatus(BetStatus status) {
        this.status = status;
    }

    public BetWinner getBetWinner() {
        return betWinner;
    }

    public void setBetWinner(BetWinner betWinner) {
        this.betWinner = betWinner;
    }

    public MatchBet toMatchBet() throws Exception {
        MatchBet matchBet = new MatchBet();
        matchBet.setIconTeam(this.getIconTeam());
        matchBet.setChallengerTeam(this.getChallengerTeam());
        matchBet.setBetType(this.getBetType());
        matchBet.setStatus(BetStatus.ACTIVE);

        // Convert String to Date
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateInString = this.getMatchDate();
        Date date = sdf.parse(dateInString);
        matchBet.setMatchDate(date);

        return matchBet;
    }
}
