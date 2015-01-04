package com.paeez.core.services.impl;

import com.paeez.core.model.UserMatchBets;
import com.paeez.core.repositories.mongo.UserMatchBetsRepository;
import com.paeez.core.services.api.UserMatchBetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Shrikant on 1/3/15.
 */
@Service
public class UserMatchBetsServiceImpl implements UserMatchBetsService {

    private UserMatchBetsRepository userMatchBetsRepository;

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    public UserMatchBetsServiceImpl(UserMatchBetsRepository userMatchBetsRepository) {
        this.userMatchBetsRepository = userMatchBetsRepository;
    }

    @Override
    public void putBet(UserMatchBets userMatchBets) {
        userMatchBetsRepository.save(userMatchBets);
    }

    @Override
    public void updateResult(UserMatchBets userMatchBets) {
        userMatchBetsRepository.save(userMatchBets);
    }

    //This just returns matchbets whose results are not updated
    @Override
    public UserMatchBets findByMatchBetId(String matchBetId) {
        return userMatchBetsRepository.findByMatchBetId(matchBetId);
    }

    @Override
    public List<UserMatchBets> findByMatchBetIdAndResultsUpdateRequired(String matchBetId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userResult").is(null).andOperator(Criteria.where("matchBetId").is(matchBetId)));
        return mongoOperations.find(query, UserMatchBets.class);
    }

    @Override
    public List<UserMatchBets> findAll() {
        return userMatchBetsRepository.findAll();
    }
}
