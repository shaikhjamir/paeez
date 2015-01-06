package com.paeez.core.services.constants;

import com.paeez.core.services.util.InputValidations;

/**
 * Created by Shrikant on 1/1/15.
 */
public enum BetWinner {
    ICONTEAM("iconTeam"),
    CHALLENGERTEAM("challengerTeam"),
    OPTIONA("optionA"),
    OPTIONB("optionB"),
    OPTIONC("optionC"),
    OPTIOND("optionD"),
    OPTIONE("optionE"),
    OPTIONF("optionF");

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
        InputValidations.validateInputIdForNull("Input id cannot be null", id);
            for (BetWinner bw : BetWinner.values()) {
                if (id.equalsIgnoreCase(bw.getBetWinnerId()))
                    return bw;
            }
        return null;
    }
}
