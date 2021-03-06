package com.paeez.core.services.constants;

import com.paeez.core.services.util.InputValidations;

/**
 * Created by Shrikant on 1/1/15.
 */
public enum BetTypes {

    MATCHBET("matchbet"),
    HIGESTSCORER("highestscorer");

    private final String id;

    public String getId() {
        return id;
    }

    BetTypes(String id) {
        this.id = id;
    }

    public static BetTypes fromStringId(String id) {
        InputValidations.validateInputIdForNull("Input id cannot be null", id);
        for (BetTypes bt : BetTypes.values()) {
            if (id.equalsIgnoreCase(bt.getId()))
                return bt;
        }
       return null;
    }
}
