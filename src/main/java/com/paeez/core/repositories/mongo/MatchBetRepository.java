package com.paeez.core.repositories.mongo;

/**
 * Created by Shrikant on 12/31/14.
 */
import com.paeez.core.model.MatchBet;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Shrikant on 12/21/14.
 */

public interface MatchBetRepository extends MongoRepository<MatchBet, String> {
    public MatchBet findById(String id);
}
