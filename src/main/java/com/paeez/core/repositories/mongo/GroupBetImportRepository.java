package com.paeez.core.repositories.mongo;


import com.paeez.core.model.GlobalBet;
import com.paeez.core.model.GroupBetImport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "groupbetsimport", path = "groupbetsimport")
public interface GroupBetImportRepository extends MongoRepository<GroupBetImport, String> {
	
	GroupBetImport findByGroupIdAndGlobalBet(@Param("groupId") String groupId, @Param("globalBet") GlobalBet globalBet) ;
	List<GroupBetImport> findByGroupId(@Param("groupId") String groupId) ;
}
