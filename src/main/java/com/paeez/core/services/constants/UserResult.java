package com.paeez.core.services.constants;

/**
 * Created by Shrikant on 1/3/15.
 */
public enum UserResult {
    WON("won"),
    LOST("lost");

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
        try {
            for (UserResult ur : UserResult.values()) {
                if (id.equalsIgnoreCase(ur.getId()))
                    return ur;
            }
        } catch (IllegalArgumentException e) {
        }
        return null;
    }
}
