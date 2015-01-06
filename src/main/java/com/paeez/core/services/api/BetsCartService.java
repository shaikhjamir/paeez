package com.paeez.core.services.api;

import com.paeez.core.model.Bet;
import com.paeez.core.model.BetsCart;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Shrikant on 1/1/15.
 */
@Service
public interface BetsCartService {

    public void save(BetsCart betsCart);
    public List<BetsCart> findAll();
    public BetsCart findById(String id);
    public BetsCart addBetToCart(String cartId, Bet betInstance);
}
