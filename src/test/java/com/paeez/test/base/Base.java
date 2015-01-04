package com.paeez.test.base;

import com.paeez.Application;
import com.paeez.core.repositories.mongo.*;
import com.paeez.core.services.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by Shrikant on 1/3/15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public abstract class Base {
    @Autowired
    public MongoOperations mongoOperations;
    @Autowired
    public MatchBetRepository matchBetRepository;
    @Autowired
    public BetsCartRepository betsCartRepository;
    @Autowired
    public GroupRepository groupRepository;
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public GroupUsersRepository groupUsersRepository;
    @Autowired
    public UserPlayedBetsRepository userPlayedBetsRepository;
    @Autowired
    public MatchBetService matchBetService;
    @Autowired
    public BetsCartServices betsCartServices;
    @Autowired
    public GroupBetImportService groupBetImportService;
    @Autowired
    public UserPlayedBetsService userPlayedBetsService;
    @Autowired
    public GenericBetService genericBetService;
}


