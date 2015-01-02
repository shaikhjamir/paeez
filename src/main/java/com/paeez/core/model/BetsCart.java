package com.paeez.core.model;

import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Created by Shrikant on 1/1/15.
 */
public class BetsCart {

    @Id
    private String id;
    //private String betType;
    private List<Bet> bets;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public String getBetType() {
//        return betType;
//    }
//
//    public void setType(String betType) {
//        this.betType = betType;
//    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
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
