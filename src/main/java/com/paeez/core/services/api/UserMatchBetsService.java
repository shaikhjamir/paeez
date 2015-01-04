package com.paeez.core.services.api;

import com.paeez.core.model.UserMatchBets;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Shrikant on 1/3/15.
 */
@Service
public interface UserMatchBetsService {
    public void putBet(UserMatchBets userMatchBets);
    public void updateResult(UserMatchBets userMatchBets);
    UserMatchBets findByMatchBetId(String matchBetId);
    List<UserMatchBets> findAll();
    public List<UserMatchBets> findByMatchBetIdAndResultsUpdateRequired(String matchBetId);

}
