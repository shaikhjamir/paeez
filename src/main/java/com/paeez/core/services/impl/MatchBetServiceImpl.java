package com.paeez.core.services.impl;


import com.paeez.core.model.MatchBet;
import com.paeez.core.repositories.mongo.MatchBetRepository;
import com.paeez.core.services.api.MatchBetService;
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
 * Created by Shrikant on 12/21/14.
 */

@Service
public class MatchBetServiceImpl extends BaseService implements MatchBetService {

    public void enterBet(MatchBet matchBet) {
        matchBetRepository.save(matchBet);
    }

    public List<MatchBet> findAll() {
        return matchBetRepository.findAll();
    }

    public MatchBet findById(String id) {
        return matchBetRepository.findById(id);
    }

    public List<MatchBet> findActive() {
        Query query = new Query();
        query.addCriteria(Criteria.where("matchDate").gt((Date) new Date()));
        return mongoOperations.find(query, MatchBet.class);
    }

    public List<MatchBet> findClosed() {
        Query query = new Query();
        query.addCriteria(Criteria.where("status").is(BetStatus.CLOSED));
        return mongoOperations.find(query, MatchBet.class);
    }
    @Override
    public void updateStatus(String id, BetStatus betStatus) {
        MatchBet matchBet = matchBetRepository.findOne(id);
        matchBet.setStatus(betStatus);
        matchBetRepository.save(matchBet);
    }

    @Override
    public void updateResult(String id, BetWinner betWinner) {
        MatchBet matchBet = matchBetRepository.findOne(id);

        matchBet.setBetWinner(betWinner);
        matchBetRepository.save(matchBet);
    }
}
