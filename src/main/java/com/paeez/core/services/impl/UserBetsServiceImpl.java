package com.paeez.core.services.impl;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.paeez.core.model.User;
import com.paeez.core.model.UserBets;
import com.paeez.core.services.api.UserBetsService;
import com.paeez.core.services.constants.UserResult;
import com.paeez.core.services.util.InputValidations;

/**
 * Created by Shrikant on 1/3/15.
 */
@Service
public class UserBetsServiceImpl extends BaseService implements UserBetsService {

	
    @Override
    public UserBets putBet(UserBets userBets) {
    	
    	InputValidations.validateForNull(userBets, "UserPlayedBets cannot be null");
        InputValidations.validateForNull(userBets.getGroupId(), "UserPlayedBets.groupId cannot be null");
        InputValidations.validateForNull(userBets.getUserId(), "UserPlayedBets.userId cannot be null");
        InputValidations.validateForNull(userBets.getGenericBetId(), "UserPlayedBets.genericBetId cannot be null");
        InputValidations.validateForNull(userBets.getBetMeasureByOptions(), "UserPlayedBets.selected bet cannot be null");
        InputValidations.validateUserExists(userBets.getUserId());
        InputValidations.validateGroupExists(userBets.getGroupId());
        
        User usr = userRepo.findOne(userBets.getUserId()) ;
        String groupId  = userBets.getGroupId() ;
        
        InputValidations.validateUserBelongsToGroup(usr, groupId);
        
        userBets.setUserResult(UserResult.AWAITED);
        userBets = userBetsRepository.save(userBets);
        return userBets;
    }

    @Override
    public UserBets updateResult(UserBets userBets) {

        InputValidations.validateForNull(userBets, "UserPlayedBets cannot be null");
        userBets = userBetsRepository.save(userBets);
        return userBets;
    }

    //This just returns bets whose results are not updated
    @Override
    public UserBets findByGenericBetId(String genericBetId) {
        InputValidations.validateInputIdForNull("genericBetId cannot be null/empty", genericBetId);

        UserBets userBets =  userBetsRepository.findByGenericBetId(genericBetId);

        InputValidations.validateForNull(userBets, "No user played bets exists for genericBetId " + genericBetId);
        return userBets;
    }

    @Override
    public List<UserBets> findByGenericBetIdAndUserResult(String genericBetId) {

        InputValidations.validateInputIdForNull("genericBetId cannot be null/empty", genericBetId);

        // trying to find where the result is awaited and BetId is equal to given BetId
        Query query = new Query();
        query.addCriteria(Criteria.where("userResult").is(UserResult.AWAITED).andOperator(Criteria.where("genericBetId").is(genericBetId)));

        return mongoOperations.find(query, UserBets.class);
    }

    @Override
    public List<UserBets> findAll() {
        List<UserBets> userBets = userBetsRepository.findAll();

        InputValidations.validateForNull(userBets, "No user played bets found");
        return userBets;
    }

    @Override
    public UserBets findById(String userBetsId) {
        InputValidations.validateInputIdForNull("userBetsId cannot be null/empty", userBetsId);

        UserBets userBets = userBetsRepository.findOne(userBetsId);
        InputValidations.validateForNull(userBets, "No user played bets found");

        return userBets;
    }
}
