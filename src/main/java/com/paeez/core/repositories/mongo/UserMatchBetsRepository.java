package com.paeez.core.repositories.mongo;

import com.paeez.core.model.UserMatchBets;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Shrikant on 1/3/15.
 */
public interface UserMatchBetsRepository extends MongoRepository<UserMatchBets, String> {
    public UserMatchBets findByMatchBetId(String matchBetId);
}
