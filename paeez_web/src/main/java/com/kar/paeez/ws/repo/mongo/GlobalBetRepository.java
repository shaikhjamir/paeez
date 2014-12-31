package com.kar.paeez.ws.repo.mongo;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.kar.paeez.ws.model.GlobalBet;

@RepositoryRestResource(collectionResourceRel = "globalbets", path = "globalbets")
public interface GlobalBetRepository extends MongoRepository<GlobalBet, String> {
}
