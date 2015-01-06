package com.paeez.core.services.impl;

import com.paeez.core.model.UserPlayedBets;
import com.paeez.core.repositories.mongo.UserPlayedBetsRepository;
import com.paeez.core.services.api.UserPlayedBetsService;
import com.paeez.core.services.util.InputValidations;
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
public class UserPlayedBetsServiceImpl extends BaseService implements UserPlayedBetsService {

    @Override
    public void putBet(UserPlayedBets userPlayedBets) {
        InputValidations.validateForNull(userPlayedBets, "UserPlayedBets cannot be null");
        userPlayedBetsRepository.save(userPlayedBets);
    }

    @Override
    public void updateResult(UserPlayedBets userPlayedBets) {

        InputValidations.validateForNull(userPlayedBets, "UserPlayedBets cannot be null");
        userPlayedBetsRepository.save(userPlayedBets);
    }

    //This just returns bets whose results are not updated
    @Override
    public UserPlayedBets findByGenericBetId(String genericBetId) {
        InputValidations.validateInputIdForNull("genericBetId cannot be null/empty", genericBetId);

        UserPlayedBets userPlayedBets =  userPlayedBetsRepository.findByGenericBetId(genericBetId);

        InputValidations.validateForNull(userPlayedBets, "No user played bets exists for genericBetId " + genericBetId);
        return userPlayedBets;
    }

    @Override
    public List<UserPlayedBets> findByGenericBetIdAndUserResult(String genericBetId) {

        InputValidations.validateInputIdForNull("genericBetId cannot be null/empty", genericBetId);

        Query query = new Query();
        query.addCriteria(Criteria.where("userResult").is(null).andOperator(Criteria.where("genericBetId").is(genericBetId)));

        return mongoOperations.find(query, UserPlayedBets.class);
    }

    @Override
    public List<UserPlayedBets> findAll() {
        List<UserPlayedBets> userPlayedBets = userPlayedBetsRepository.findAll();

        InputValidations.validateForNull(userPlayedBets, "No user played bets found");
        return userPlayedBets;
    }
}
