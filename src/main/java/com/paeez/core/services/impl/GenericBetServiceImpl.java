package com.paeez.core.services.impl;

import com.paeez.core.model.GenericBet;
import com.paeez.core.repositories.mongo.GenericBetRepository;
import com.paeez.core.services.api.GenericBetService;
import com.paeez.core.services.constants.BetStatus;
import com.paeez.core.services.constants.BetWinner;
import com.paeez.core.services.exceptions.InvalidGenericBetEntryEnception;
import com.paeez.core.services.util.InputValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
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

//    @Autowired
//    private GenericBetRepository genericBetRepository;
//
//    @Autowired
//    private MongoOperations mongoOperations;

    @Override
    public void enterBet(GenericBet genericBet) {
        InputValidations.validateGenericBetForNull(genericBet, "Error in entering generic bet");
        genericBetRepository.save(genericBet);
    }

    @Override
    public List<GenericBet> findAll() {
        List<GenericBet> genericBets = genericBetRepository.findAll();
        InputValidations.checkGenericBetsFound(genericBets, "No generic bets available in store");
        return genericBets;
    }

    @Override
    public GenericBet findById(String id) {
        GenericBet genericBet = genericBetRepository.findById(id);
        InputValidations.checkGenericBetFound(genericBet, id);
        return genericBet;
    }

    @Override
    public List<GenericBet> findActive() {
        Query query = new Query();
        query.addCriteria(Criteria.where("closingTime").gt((Date) new Date()));
        List<GenericBet> genericBets = mongoOperations.find(query, GenericBet.class);
        InputValidations.checkGenericBetsFound(genericBets, "No active generic bets available in store");
        return genericBets;
    }

    @Override
    public List<GenericBet> findClosed() {
        Query query = new Query();
        query.addCriteria(Criteria.where("status").is(BetStatus.CLOSED));
        List<GenericBet> genericBets = mongoOperations.find(query, GenericBet.class);
        InputValidations.checkGenericBetsFound(genericBets, "No closed generic bets available in store");
        return genericBets;
    }

    @Override
    public void updateStatus(String id, BetStatus betStatus) {
        GenericBet genericBet = genericBetRepository.findOne(id);
        InputValidations.checkGenericBetFound(genericBet, "No generic bet found with genericBetId " + id +
                " , failure in updating status");

        genericBet.setStatus(betStatus);
        genericBetRepository.save(genericBet);
    }

    @Override
    public void updateResult(String id, BetWinner winningOption) {
        GenericBet genericBet = genericBetRepository.findOne(id);
        InputValidations.checkGenericBetFound(genericBet, "No generic bet found with genericBetId " + id +
                " , failure in updating result");

        genericBet.setWinningOption(winningOption);
        genericBetRepository.save(genericBet);
    }
}

