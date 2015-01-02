package com.paeez.core.repositories.mongo;


import com.paeez.core.model.GroupUsers;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "groupsusers", path = "groupsusers")
public interface GroupUsersRepository extends MongoRepository<GroupUsers, String> {

	GroupUsers findByGroupIdAndUserEmailAddress(@Param("groupId") String groupId, @Param("userEmailAddress") String userEmailAddress) ;
	
	List<GroupUsers> findByGroupId(@Param("groupId") String groupId) ;
	
	List<GroupUsers> findByUserEmailAddress(@Param("userEmailAddress") String userEmailAddress) ;

}
