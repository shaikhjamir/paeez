package com.paeez.core.services.api;

import com.paeez.core.model.UserPlayedBets;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Shrikant on 1/3/15.
 */
@Service
public interface UserPlayedBetsService {
    public void putBet(UserPlayedBets userPlayedBets);
    public void updateResult(UserPlayedBets userPlayedBets);
    UserPlayedBets findByGenericBetId(String genericBetId);
    List<UserPlayedBets> findAll();
    public List<UserPlayedBets> findByGenericBetIdAndUserResult(String genericBetId);

}
