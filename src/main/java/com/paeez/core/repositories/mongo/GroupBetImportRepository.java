package com.paeez.core.repositories.mongo;

import com.paeez.core.model.GroupBetImport;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GroupBetImportRepository extends MongoRepository<GroupBetImport, String> {
	
	GroupBetImport findByGroupIdAndBetsCartId(String groupId, String betsCartId) ;
	GroupBetImport findByGroupId(String groupId) ;
}
