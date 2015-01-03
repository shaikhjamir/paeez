package com.paeez.core.services.api;

import com.paeez.core.model.GroupBetImport;

import java.util.List;

/**
 * Created by Shrikant on 1/2/15.
 */
public interface GroupBetImportService {
    GroupBetImport findByGroupIdAndBetsCartId(String groupId, String betsCartId) ;
    GroupBetImport findByGroupId(String groupId) ;
    List<GroupBetImport> findByAll() ;
    public void update(GroupBetImport groupBetImport);
}
