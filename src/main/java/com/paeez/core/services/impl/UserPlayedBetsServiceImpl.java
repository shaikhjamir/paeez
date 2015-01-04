package com.paeez.core.services.impl;

import com.paeez.core.model.UserPlayedBets;
import com.paeez.core.repositories.mongo.UserPlayedBetsRepository;
import com.paeez.core.services.api.UserPlayedBetsService;
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
public class UserPlayedBetsServiceImpl implements UserPlayedBetsService {

    private UserPlayedBetsRepository userPlayedBetsRepository;

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    public UserPlayedBetsServiceImpl(UserPlayedBetsRepository userPlayedBetsRepository) {
        this.userPlayedBetsRepository = userPlayedBetsRepository;
    }

    @Override
    public void putBet(UserPlayedBets userPlayedBets) {
        userPlayedBetsRepository.save(userPlayedBets);
    }

    @Override
    public void updateResult(UserPlayedBets userPlayedBets) {
        userPlayedBetsRepository.save(userPlayedBets);
    }

    //This just returns bets whose results are not updated
    @Override
    public UserPlayedBets findByGenericBetId(String genericBetId) {
        return userPlayedBetsRepository.findByGenericBetId(genericBetId);
    }

    @Override
    public List<UserPlayedBets> findByGenericBetIdAndUserResult(String genericBetId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userResult").is(null).andOperator(Criteria.where("genericBetId").is(genericBetId)));
        return mongoOperations.find(query, UserPlayedBets.class);
    }

    @Override
    public List<UserPlayedBets> findAll() {
        return userPlayedBetsRepository.findAll();
    }
}
