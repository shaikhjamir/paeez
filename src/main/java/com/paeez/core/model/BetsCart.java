package com.paeez.core.model;

import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Created by Shrikant on 1/1/15.
 */
public class BetsCart {

    @Id
    private String id;
    private List<GenericBet> bets;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public List<GenericBet> getBets() {
        return bets;
    }
    public void setBets(List<GenericBet> bets) {
        this.bets = bets;
    }

    @Override
    public String toString() {
        return "BetsCart{" +
                "id='" + id + '\'' +
                ", bets=" + bets +
                '}';
    }
}
