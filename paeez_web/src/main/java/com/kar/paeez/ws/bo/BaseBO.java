package com.kar.paeez.ws.bo;

import org.springframework.beans.factory.annotation.Autowired;

import com.kar.paeez.ws.repo.mongo.GroupAdminUsersRepository;
import com.kar.paeez.ws.repo.mongo.GroupRepository;
import com.kar.paeez.ws.repo.mongo.UserRepository;


abstract public class BaseBO {
	
	@Autowired
	protected GroupAdminUsersRepository groupAdminUsersRepo;

	@Autowired
	protected UserRepository userRepo;

	@Autowired
	protected GroupRepository groupRepo;
}

