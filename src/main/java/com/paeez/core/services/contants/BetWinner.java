package com.paeez.core.services.contants;

/**
 * Created by Shrikant on 1/1/15.
 */
public enum BetWinner {
    ICONTEAM("iconTeam"),
    CHALLENGERTEAM("challengerTeam");

    private String betWinnerId;

    public String getBetWinnerId() {
        return betWinnerId;
    }

    public void setBetWinnerId(String betWinnerId) {
        this.betWinnerId = betWinnerId;
    }

    BetWinner(String betWinnerId) {
        this.betWinnerId = betWinnerId;
    }

    public static BetWinner fromStringId(String id) {
        try {
            for (BetWinner bw : BetWinner.values()) {
                if (id.equalsIgnoreCase(bw.getBetWinnerId()))
                    return bw;
            }
        } catch (IllegalArgumentException e) {
        }
        return null;
    }
}
