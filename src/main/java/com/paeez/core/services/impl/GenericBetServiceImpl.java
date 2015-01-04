package com.paeez.core.services.impl;

import com.paeez.core.model.GenericBet;
import com.paeez.core.repositories.mongo.GenericBetRepository;
import com.paeez.core.services.api.GenericBetService;
import com.paeez.core.services.constants.BetStatus;
import com.paeez.core.services.constants.BetWinner;
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
public class GenericBetServiceImpl implements GenericBetService {

    @Autowired
    private GenericBetRepository genericBetRepository;

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public void enterBet(GenericBet genericBet) {
        genericBetRepository.save(genericBet);
    }

    @Override
    public List<GenericBet> findAll() {
        return genericBetRepository.findAll();
    }

    @Override
    public GenericBet findById(String id) {
        return genericBetRepository.findById(id);
    }

    @Override
    public List<GenericBet> findActive() {
        Query query = new Query();
        query.addCriteria(Criteria.where("closingTime").gt((Date) new Date()));
        return mongoOperations.find(query, GenericBet.class);
    }

    @Override
    public List<GenericBet> findClosed() {
        Query query = new Query();
        query.addCriteria(Criteria.where("status").is(BetStatus.CLOSED));
        return mongoOperations.find(query, GenericBet.class);
    }

    @Override
    public void updateStatus(String id, BetStatus betStatus) {
        GenericBet genericBet = genericBetRepository.findOne(id);
        genericBet.setStatus(betStatus);
        genericBetRepository.save(genericBet);

    }

    @Override
    public void updateResult(String id, BetWinner winningOption) {
        GenericBet matchBet = genericBetRepository.findOne(id);
        matchBet.setWinningOption(winningOption);
        genericBetRepository.save(matchBet);
    }
}

