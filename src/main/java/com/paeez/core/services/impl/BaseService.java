package com.paeez.core.services.impl;

import com.paeez.core.repositories.mongo.*;
import org.springframework.beans.factory.annotation.Autowired;


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
	protected GlobalBetRepository globalBetRepo ;
	
	@Autowired
	protected GroupBetImportRepository groupGlobalBetImportRepo ;
	
}

