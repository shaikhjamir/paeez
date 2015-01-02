package com.paeez.core.services.contants;

/**
 * Created by Shrikant on 1/1/15.
 */
public enum BetStatus {
    ACTIVE("active"),
    CLOSED("closed"),
    EXPIRED("expired"),
    ERROR("error");

    private String statusId;

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    private BetStatus(String statusId) {
        this.statusId = statusId;
    }

    public static BetStatus fromStringId(String id) {
        try {
            for (BetStatus bs : BetStatus.values()) {
                if (id.equalsIgnoreCase(bs.getStatusId()))
                    return bs;
            }
        } catch (IllegalArgumentException e) {
        }
        return null;
    }
}
