package com.paeez.core.repositories.mongo;

import com.paeez.core.model.UserBets;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Shrikant on 1/3/15.
 */
public interface UserBetsRepository extends MongoRepository<UserBets, String> {
    public UserBets findByGenericBetId(String genericBetId);
    public UserBets findByUserIdAndGroupIdAndGenericBetId(@Param("userId") String userId, @Param("groupId") String groupId, 
    		@Param("genericBetId") String genericBetId);
}
