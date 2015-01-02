package com.paeez.core.services.api;


import com.paeez.core.model.MatchBet;
import com.paeez.core.services.contants.BetStatus;
import com.paeez.core.services.contants.BetWinner;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Shrikant on 12/21/14.
 */
@Service
public interface MatchBetServices {

    public void enterBet(MatchBet matchBet);
    public List<MatchBet> findAll();
    public MatchBet findById(String id);

    public List<MatchBet> findActive();

    public void updateStatus(String id, BetStatus betStatus);
    public void updateResult(String id, BetWinner betWinner);
}
