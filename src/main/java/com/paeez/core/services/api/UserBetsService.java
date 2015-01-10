package com.paeez.core.services.api;

import com.paeez.core.model.UserBets;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Shrikant on 1/3/15.
 */
@Service
public interface UserBetsService {
    public UserBets putBet(UserBets userBets);
    public UserBets updateResult(UserBets userBets);
    UserBets findByGenericBetId(String genericBetId);
    List<UserBets> findAll();
    public List<UserBets> findByGenericBetIdAndUserResult(String genericBetId);
    public UserBets findById(String userBetsId);

}
