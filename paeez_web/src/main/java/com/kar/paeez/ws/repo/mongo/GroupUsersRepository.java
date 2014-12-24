package com.kar.paeez.ws.repo.mongo;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.kar.paeez.ws.model.GroupUsers;

@RepositoryRestResource(collectionResourceRel = "groupsusers", path = "groupsusers")
public interface GroupUsersRepository extends MongoRepository<GroupUsers, String> {

}
