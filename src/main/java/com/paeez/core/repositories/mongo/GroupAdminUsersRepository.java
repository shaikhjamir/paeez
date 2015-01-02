package com.paeez.core.repositories.mongo;


import com.paeez.core.model.GroupAdminUsers;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "groupsadminusers", path = "groupsadminusers")
public interface GroupAdminUsersRepository extends MongoRepository<GroupAdminUsers, String> {

	GroupAdminUsers findByGroupIdAndUserEmailAddress(@Param("groupId") String groupId, @Param("userEmailAddress") String userEmailAddress) ;
	
	List<GroupAdminUsers> findByGroupId(@Param("groupId") String groupId) ;
	
	List<GroupAdminUsers> findByUserEmailAddress(@Param("userEmailAddress") String userEmailAddress) ;
}
