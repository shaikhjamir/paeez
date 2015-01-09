package com.paeez.rest.resources;

import com.paeez.core.model.BetsCart;
import com.paeez.core.model.GenericBet;
import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shrikant on 1/1/15.
 */
public class BetsCartResource extends ResourceSupport {

    private List<GenericBet> bets;
    public List<GenericBet> getBets() {
        return bets;
    }
    public void setBets(List<GenericBet> bets) {
        this.bets = bets;
    }

    public BetsCart toBetsCart() throws Exception {
        //in create we are just creating empty bets list.
        BetsCart betsCart = new BetsCart();
        List<GenericBet> bets = new ArrayList<>();
        betsCart.setBets(bets);
        return betsCart;
    }
}
