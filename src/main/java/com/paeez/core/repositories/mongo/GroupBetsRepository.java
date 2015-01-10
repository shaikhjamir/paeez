package com.paeez.core.repositories.mongo;

import com.paeez.core.model.GroupBets;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Shrikant on 1/1/15.
 */
public interface GroupBetsRepository extends MongoRepository<GroupBets, String> {
    public GroupBets findByIdAndGroupId(String groupId, String betsCartId) ;
    public GroupBets findByGroupId(String groupId) ;
}
