package com.paeez.core.services.api;

import com.paeez.core.model.GroupBets;
import com.paeez.core.model.GenericBet;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Shrikant on 1/1/15.
 */
@Service
public interface GroupBetsService {

    public GroupBets save(GroupBets groupBets);
    public List<GroupBets> findAll();
    public GroupBets findById(String id);
    public GroupBets addBetToCart(String cartId, GenericBet betInstance);
}
