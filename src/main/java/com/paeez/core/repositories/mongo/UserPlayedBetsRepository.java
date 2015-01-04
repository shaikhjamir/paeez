package com.paeez.core.repositories.mongo;

import com.paeez.core.model.UserPlayedBets;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Shrikant on 1/3/15.
 */
public interface UserPlayedBetsRepository extends MongoRepository<UserPlayedBets, String> {
    public UserPlayedBets findByGenericBetId(String genericBetId);
}
