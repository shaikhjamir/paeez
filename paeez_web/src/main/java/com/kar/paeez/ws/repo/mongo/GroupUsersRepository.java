package com.kar.paeez.ws.repo.mongo;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.kar.paeez.ws.model.GroupUsers;

@RepositoryRestResource(collectionResourceRel = "groupsusers", path = "groupsusers")
public interface GroupUsersRepository extends MongoRepository<GroupUsers, String> {

	GroupUsers findByGroupIdAndUserEmailAddress(@Param("groupId") String groupId, @Param("userEmailAddress") String userEmailAddress) ;
	
	List<GroupUsers> findByGroupId(@Param("groupId") String groupId) ;
	
	List<GroupUsers> findByUserEmailAddress(@Param("userEmailAddress") String userEmailAddress) ;

}
