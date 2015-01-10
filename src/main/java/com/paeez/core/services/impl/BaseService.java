package com.paeez.core.services.impl;

import com.paeez.core.repositories.mongo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;


abstract public class BaseService {
	
	@Autowired
	protected GroupAdminUsersRepository groupAdminUsersRepo;

	@Autowired
	protected GroupUsersRepository groupUsersRepo;
	
	@Autowired
	protected UserRepository userRepo;

	@Autowired
	protected GroupRepository groupRepo;

	@Autowired
	protected GroupBetsRepository groupBetsRepository;

	@Autowired
	protected GenericBetRepository genericBetRepository;

	@Autowired
	protected MongoOperations mongoOperations;

	@Autowired
	protected UserBetsRepository userBetsRepository;

}

