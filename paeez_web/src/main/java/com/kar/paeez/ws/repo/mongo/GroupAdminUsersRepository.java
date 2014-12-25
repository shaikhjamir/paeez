package com.kar.paeez.ws.repo.mongo;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.kar.paeez.ws.model.GroupAdminUsers;

@RepositoryRestResource(collectionResourceRel = "groupsadminusers", path = "groupsadminusers")
public interface GroupAdminUsersRepository extends MongoRepository<GroupAdminUsers, String> {

	GroupAdminUsers findByGroupAndUserEmailAddress(@Param("group") String group, @Param("userEmailAddress") String userEmailAddress) ;
}
