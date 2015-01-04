package com.paeez.core.services.impl;

import com.paeez.core.model.Bet;
import com.paeez.core.model.BetsCart;
import com.paeez.core.repositories.mongo.BetsCartRepository;
import com.paeez.core.services.api.BetsCartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shrikant on 1/1/15.
 */
@Service
public class BetsCartServiceImpl implements BetsCartServices {

    private BetsCartRepository betsCartRepository;

    @Autowired
    public BetsCartServiceImpl(BetsCartRepository betsCartRepository) {
        this.betsCartRepository = betsCartRepository;
    }

    @Override
    public void save(BetsCart betsCart) {
        betsCartRepository.save(betsCart);
    }

    @Override
    public List<BetsCart> findAll() {
        return betsCartRepository.findAll();
    }

    @Override
    public BetsCart findById(String id) {
        return betsCartRepository.findById(id);
    }

    @Override
    public BetsCart addBetToCart(String cartId, Bet betInstance) {
        BetsCart betsCart = findById(cartId);

        List <Bet> bets = betsCart.getBets();
        if (bets == null)
            bets = new ArrayList<Bet>();
        bets.add(betInstance);
        betsCartRepository.save(betsCart);

        return betsCart;
    }
}
