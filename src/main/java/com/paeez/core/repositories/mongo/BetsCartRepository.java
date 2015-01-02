package com.paeez.core.repositories.mongo;

import com.paeez.core.model.BetsCart;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Shrikant on 1/1/15.
 */
public interface BetsCartRepository extends MongoRepository<BetsCart, String> {
    public BetsCart findById(String id);
}
