package com.paeez.rest.resources;

import org.springframework.hateoas.ResourceSupport;

/**
 * Created by Shrikant on 1/1/15.
 */
public class BetIdResource extends ResourceSupport {

    private String betId;
    public String getBetId() {
        return betId;
    }

    public void setId(String betId) {
        this.betId = betId;
    }
}
