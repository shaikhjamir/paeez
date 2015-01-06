package com.paeez.core.services.impl;

import com.paeez.core.model.Bet;
import com.paeez.core.model.BetsCart;
import com.paeez.core.repositories.mongo.BetsCartRepository;
import com.paeez.core.services.api.BetsCartService;
import com.paeez.core.services.util.InputValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shrikant on 1/1/15.
 */
@Service
public class BetsCartServiceImpl extends BaseService implements BetsCartService {

    @Override
    public void save(BetsCart betsCart) {
        InputValidations.validateBetsCartForNull(betsCart, "Null betsCart send, failure in BetsCartService.save");
        betsCartRepository.save(betsCart);
    }

    @Override
    public List<BetsCart> findAll() {
        List<BetsCart> betsCarts = betsCartRepository.findAll();

        InputValidations.checkBetsCartsFound(betsCarts, "No betsCart found in store");
        return betsCarts;
    }

    @Override
    public BetsCart findById(String id) {
        InputValidations.validateInputIdForNull("BetsCartId cannot be null or empty", id);
        BetsCart betsCart = betsCartRepository.findById(id);
        InputValidations.checkBetsCartFound(betsCart, "No betsCart found for given betsCartId " + id);
        return betsCart;
    }

    @Override
    public BetsCart addBetToCart(String cartId, Bet betInstance) {
        InputValidations.validateInputIdForNull("BetsCartId cannot be null or empty in BetsCartService.addBetToCart",
                                                cartId);

        BetsCart betsCart = findById(cartId);

        InputValidations.checkBetsCartFound(betsCart, "No betsCart found for given betsCartId " + cartId);

        List <Bet> bets = betsCart.getBets();
        if (bets == null)
            bets = new ArrayList<Bet>();
        bets.add(betInstance);
        betsCartRepository.save(betsCart);

        return betsCart;
    }
}
