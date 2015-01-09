package com.paeez.core.services.constants;

import com.paeez.core.services.util.InputValidations;

/**
 * Created by Shrikant on 1/1/15.
 */
public enum BetOptions {
    OPTIONA("optionA"),
    OPTIONB("optionB"),
    OPTIONC("optionC"),
    OPTIOND("optionD"),
    OPTIONE("optionE"),
    OPTIONF("optionF");

    private String betOptionId;

    private BetOptions(String betOptionId) {
        this.betOptionId = betOptionId;
    }

    public String getBetOptionId() {
        return betOptionId;
    }

    public void setBetOptionId(String betOptionId) {
        this.betOptionId = betOptionId;
    }

    public static BetOptions fromStringId(String id) {
        InputValidations.validateInputIdForNull("Input id cannot be null", id);
            for (BetOptions bw : BetOptions.values()) {
                if (id.equalsIgnoreCase(bw.getBetOptionId()))
                    return bw;
            }
        return null;
    }
}
