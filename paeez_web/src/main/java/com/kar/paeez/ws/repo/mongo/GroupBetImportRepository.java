package com.kar.paeez.ws.repo.mongo;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.kar.paeez.ws.model.GlobalBet;
import com.kar.paeez.ws.model.GroupBetImport;

@RepositoryRestResource(collectionResourceRel = "groupbetsimport", path = "groupbetsimport")
public interface GroupBetImportRepository extends MongoRepository<GroupBetImport, String> {
	
	GroupBetImport findByGroupIdAndGlobalBet(@Param("groupId") String groupId, @Param("globalBet") GlobalBet globalBet) ;
	List<GroupBetImport> findByGroupId(@Param("groupId") String groupId) ;
}
