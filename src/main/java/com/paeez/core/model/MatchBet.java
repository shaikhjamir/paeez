package com.paeez.core.model;

import com.paeez.core.services.constants.BetStatus;
import com.paeez.core.services.constants.BetTypes;
import com.paeez.core.services.constants.BetWinner;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by Shrikant on 12/31/14.
 */
public class MatchBet implements Bet {
    @Id
    private String id;

    private String iconTeam;
    private String challengerTeam;
    private Date matchDate;
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

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "MatchBet{" +
                "id='" + id + '\'' +
                ", iconTeam='" + iconTeam + '\'' +
                ", challengerTeam='" + challengerTeam + '\'' +
                ", matchDate=" + matchDate +
                ", betType=" + betType +
                ", status=" + status +
                ", betWinner=" + betWinner +
                '}';
    }
}
