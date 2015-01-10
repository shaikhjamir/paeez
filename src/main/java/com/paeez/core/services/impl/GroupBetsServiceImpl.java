package com.paeez.core.services.impl;

import com.paeez.core.model.GroupBets;
import com.paeez.core.model.GenericBet;
import com.paeez.core.services.api.GroupBetsService;
import com.paeez.core.services.exceptions.GroupBetsDoesNotExistsException;
import com.paeez.core.services.util.InputValidations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shrikant on 1/1/15.
 */
@Service
public class GroupBetsServiceImpl extends BaseService implements GroupBetsService {

    @Override
    public GroupBets save(GroupBets groupBets) {
        InputValidations.validateForNull(groupBets, "Null betsCart send, failure in BetsCartService.save");
        groupBets = groupBetsRepository.save(groupBets);
        return groupBets;
    }

    @Override
    public List<GroupBets> findAll() {
        List<GroupBets> groupBetses = groupBetsRepository.findAll();
        if (groupBetses == null)
            throw new GroupBetsDoesNotExistsException("No betsCart found in store");

        return groupBetses;
    }

    @Override
    public GroupBets findById(String id) {
        InputValidations.validateInputIdForNull("BetsCartId cannot be null or empty", id);
        GroupBets groupBets = groupBetsRepository.findOne(id);

        if (groupBets == null)
            throw new GroupBetsDoesNotExistsException("No betsCart found for given betsCartId " + id);

        return groupBets;
    }

    @Override
    public GroupBets addBetToCart(String cartId, GenericBet betInstance) {
        InputValidations.validateInputIdForNull("BetsCartId cannot be null or empty in BetsCartService.addBetToCart",
                                                cartId);

        InputValidations.validateForNull(betInstance, "BetInstance cannot be null");

        GroupBets groupBets = findById(cartId);

        if (groupBets == null)
            throw new GroupBetsDoesNotExistsException("No betsCart found for given betsCartId " + cartId);

        List <String> genericBetIds = groupBets.getGenericBetIds();
        if (genericBetIds == null)
            genericBetIds = new ArrayList<String>();
        // Duplicate check to be done
        // foreach {compare }

        genericBetIds.add(betInstance.getId());
        groupBetsRepository.save(groupBets);

        return groupBets;
    }
}
