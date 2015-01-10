package com.paeez.core.services.impl;

import com.paeez.core.model.GenericBet;
import com.paeez.core.services.api.GenericBetService;
import com.paeez.core.services.constants.BetStatus;
import com.paeez.core.services.constants.BetOptions;
import com.paeez.core.services.exceptions.GenericBetDoesNotExistException;
import com.paeez.core.services.util.InputValidations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Shrikant on 1/3/15.
 */
@Service
public class GenericBetServiceImpl extends BaseService implements GenericBetService {

    @Override
    public GenericBet saveBet(GenericBet genericBet) {
        InputValidations.validateForNull(genericBet, "Error in entering generic bet");
        genericBet.setCreatedTime(new Date());
        genericBet = genericBetRepository.save(genericBet);
        return genericBet;
    }

    @Override
    public List<GenericBet> findAll() {
        List<GenericBet> genericBets = genericBetRepository.findAll();

        if (genericBets == null || genericBets.size() == 0)
            throw new GenericBetDoesNotExistException("No generic bets available in store");

        return genericBets;
    }

    @Override
    public GenericBet findById(String id) {

        InputValidations.validateInputIdForNull("GenericBetId cannot be null/empty", id);
        GenericBet genericBet = genericBetRepository.findById(id);

        if (genericBet == null)
            throw new GenericBetDoesNotExistException("GenericBet does not exists with id " + id);

        return genericBet;
    }

    @Override
    public List<GenericBet> findActive() {
        Query query = new Query();
        query.addCriteria(Criteria.where("closingTime").gt((Date) new Date()));
        List<GenericBet> genericBets = mongoOperations.find(query, GenericBet.class);

        if (genericBets == null || genericBets.size() == 0)
            throw new GenericBetDoesNotExistException("No active generic bets available in store");

        return genericBets;
    }

    @Override
    public List<GenericBet> findClosed() {
        Query query = new Query();
        query.addCriteria(Criteria.where("status").is(BetStatus.CLOSED));
        List<GenericBet> genericBets = mongoOperations.find(query, GenericBet.class);

        if (genericBets == null || genericBets.size() == 0)
            throw new GenericBetDoesNotExistException("No closed generic bets available in store");

        return genericBets;
    }

    @Override
    public GenericBet updateStatus(String id, BetStatus betStatus) {
        InputValidations.validateInputIdForNull("GenericBetId cannot be null/empty", id);

        GenericBet genericBet = genericBetRepository.findOne(id);

        if (genericBet == null)
            throw new GenericBetDoesNotExistException("No generic bet found with genericBetId " + id +
                    " , failure in updating status");

        genericBet.setStatus(betStatus);
        genericBet = genericBetRepository.save(genericBet);
        return genericBet;
    }

    @Override
    public GenericBet updateResult(String id, BetOptions winningOption) {
        InputValidations.validateInputIdForNull("GenericBetId cannot be null/empty", id);

        GenericBet genericBet = genericBetRepository.findOne(id);

        if (genericBet == null)
            throw new GenericBetDoesNotExistException("No generic bet found with genericBetId " + id +
                    " , failure in updating result");

        genericBet.setWinningOption(winningOption);
        genericBet = genericBetRepository.save(genericBet);
        return genericBet;
    }
}

