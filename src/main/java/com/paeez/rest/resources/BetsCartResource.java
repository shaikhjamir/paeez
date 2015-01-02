package com.paeez.rest.resources;

import com.paeez.core.model.Bet;
import com.paeez.core.model.BetsCart;
import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shrikant on 1/1/15.
 */
public class BetsCartResource extends ResourceSupport {

   // private String betType;
    private List<Bet> bets;

//    public String getBetType() {
//        return betType;
//    }
//
//    public void setBetType(String type) {
//        this.betType = betType;
//    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }

    public BetsCart toBetsCart() throws Exception {
        //in create we are just creating empty bets list.
        BetsCart betsCart = new BetsCart();
        List<Bet> bets = new ArrayList<>();
        //betsCart.setType(this.getBetType());
        //betsCart.setBets(this.getBets());
        betsCart.setBets(bets);
        return betsCart;
    }
}
