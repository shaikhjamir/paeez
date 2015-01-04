package com.paeez.core.services.api;

import com.paeez.core.model.GenericBet;
import com.paeez.core.model.MatchBet;
import com.paeez.core.services.constants.BetStatus;
import com.paeez.core.services.constants.BetWinner;

import java.util.List;

/**
 * Created by Shrikant on 1/3/15.
 */
public interface GenericBetService {
    public void enterBet(GenericBet genericBet);
    public List<GenericBet> findAll();
    public GenericBet findById(String id);

    public List<GenericBet> findActive();
    public List<GenericBet> findClosed();

    public void updateStatus(String id, BetStatus betStatus);
    public void updateResult(String id, String winningOption);
}

