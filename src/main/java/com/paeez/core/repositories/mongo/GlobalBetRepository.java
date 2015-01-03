package com.paeez.core.repositories.mongo;


import com.paeez.core.model.GenericBet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "globalbets", path = "globalbets")
public interface GlobalBetRepository extends MongoRepository<GenericBet, String> {
}
