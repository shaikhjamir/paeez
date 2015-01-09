package com.paeez.core.services.api;

import com.paeez.core.model.UserBets;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Shrikant on 1/3/15.
 */
@Service
public interface UserBetsService {
    public void putBet(UserBets userBets);
    public void updateResult(UserBets userBets);
    UserBets findByGenericBetId(String genericBetId);
    List<UserBets> findAll();
    public List<UserBets> findByGenericBetIdAndUserResult(String genericBetId);
    public UserBets findById(String userBetsId);

}
