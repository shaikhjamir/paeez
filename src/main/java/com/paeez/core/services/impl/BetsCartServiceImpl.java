package com.paeez.core.services.impl;

import com.paeez.core.model.BetsCart;
import com.paeez.core.model.GenericBet;
import com.paeez.core.repositories.mongo.BetsCartRepository;
import com.paeez.core.services.api.BetsCartService;
import com.paeez.core.services.exceptions.BetsCartDoesNotExistsException;
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
        InputValidations.validateForNull(betsCart, "Null betsCart send, failure in BetsCartService.save");
        betsCartRepository.save(betsCart);
    }

    @Override
    public List<BetsCart> findAll() {
        List<BetsCart> betsCarts = betsCartRepository.findAll();
        if (betsCarts == null)
            throw new BetsCartDoesNotExistsException("No betsCart found in store");

        return betsCarts;
    }

    @Override
    public BetsCart findById(String id) {
        InputValidations.validateInputIdForNull("BetsCartId cannot be null or empty", id);
        BetsCart betsCart = betsCartRepository.findById(id);

        if (betsCart == null)
            throw new BetsCartDoesNotExistsException("No betsCart found for given betsCartId " + id);

        return betsCart;
    }

    @Override
    public BetsCart addBetToCart(String cartId, GenericBet betInstance) {
        InputValidations.validateInputIdForNull("BetsCartId cannot be null or empty in BetsCartService.addBetToCart",
                                                cartId);

        InputValidations.validateForNull(betInstance, "BetInstance cannot be null");

        BetsCart betsCart = findById(cartId);

        if (betsCart == null)
            throw new BetsCartDoesNotExistsException("No betsCart found for given betsCartId " + cartId);

        List <GenericBet> bets = betsCart.getBets();
        if (bets == null)
            bets = new ArrayList<GenericBet>();
        // Duplicate check to be done
        // foreach {compare }

        bets.add(betInstance);
        betsCartRepository.save(betsCart);

        return betsCart;
    }
}
