package com.kar.paeez.ws.repo.mongo;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.kar.paeez.ws.model.Group;

@RepositoryRestResource(collectionResourceRel = "groups", path = "groups")
public interface GroupRepository extends MongoRepository<Group, String> {
}
