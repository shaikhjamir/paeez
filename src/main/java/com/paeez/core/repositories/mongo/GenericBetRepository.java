package com.paeez.core.repositories.mongo;

import com.paeez.core.model.GenericBet;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Shrikant on 1/3/15.
 */
public interface GenericBetRepository extends MongoRepository<GenericBet, String> {
    public GenericBet findById(String id);
}
