package com.paeez.core.services.constants;

import com.paeez.core.services.util.InputValidations;

/**
 * Created by Shrikant on 1/3/15.
 */
public enum UserResult {
    WON("won"),
    LOST("lost"),
    AWAITED("awaited");

    private String Id;

    private UserResult(String id) {
        Id = id;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public static UserResult fromStringId(String id) {
        InputValidations.validateInputIdForNull("Input id cannot be null", id);
        for (UserResult ur : UserResult.values()) {
                if (id.equalsIgnoreCase(ur.getId()))
                    return ur;
        }
        return null;
    }
}
