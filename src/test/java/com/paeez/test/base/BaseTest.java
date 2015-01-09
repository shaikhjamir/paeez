package com.paeez.test.base;

import com.paeez.Application;
import com.paeez.core.repositories.mongo.*;
import com.paeez.core.services.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;

/**
 * Created by Shrikant on 1/3/15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public abstract class BaseTest {
    @Autowired
    protected MongoOperations mongoOperations;
    @Autowired
    protected BetsCartRepository betsCartRepository;
    @Autowired
    protected GroupRepository groupRepository;
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected GroupUsersRepository groupUsersRepository;
    @Autowired
    protected UserPlayedBetsRepository userPlayedBetsRepository;
    @Autowired
    protected BetsCartService betsCartService;
    @Autowired
    protected GroupBetImportService groupBetImportService;
    @Autowired
    protected UserPlayedBetsService userPlayedBetsService;
    @Autowired
    protected GenericBetService genericBetService;

    @Autowired
    protected GenericBetRepository genericBetRepository;

    protected MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    protected HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    @Autowired
    protected void setConverters(HttpMessageConverter<?>[] converters) {
        for (HttpMessageConverter hmc : converters) {
            if (hmc instanceof MappingJackson2HttpMessageConverter)
                this.mappingJackson2HttpMessageConverter = hmc;
        }
    }
}


