package com.paeez.test.unit.rest.controllers;

import com.paeez.core.model.GenericBet;
import com.paeez.core.services.api.GenericBetService;
import com.paeez.core.services.constants.BetTypes;
import com.paeez.core.services.exceptions.GenericBetDoesNotExistException;
import com.paeez.rest.controllers.GenericBetController;
import com.paeez.rest.exceptions.advices.ExceptionHandlerControllerAdvice;
import com.paeez.test.base.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Shrikant on 1/6/15.
 */
public class GenericBetControllerTest extends BaseTest {

    @InjectMocks
    private GenericBetController genericBetControllerMock;

    @Mock
    private GenericBetService genericBetServiceMock;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);

        final StaticApplicationContext applicationContext = new StaticApplicationContext();
        applicationContext.registerSingleton("exceptionHandler", ExceptionHandlerControllerAdvice.class);
        final WebMvcConfigurationSupport webMvcConfigurationSupport = new WebMvcConfigurationSupport();
        webMvcConfigurationSupport.setApplicationContext(applicationContext);

        this.mockMvc = MockMvcBuilders.standaloneSetup(genericBetControllerMock).
                setHandlerExceptionResolvers(webMvcConfigurationSupport.handlerExceptionResolver()).
                build();

        this.genericBetRepository.deleteAll();
    }

    @Test
    public void testGenericBetNotFound() throws Exception {

        when(genericBetServiceMock.findAll()).thenThrow(new GenericBetDoesNotExistException("No generic bet found in mock store"));

        mockMvc.perform(get("/bets/")
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGenericBetFindAll() throws Exception {

        GenericBet genericBet = new GenericBet();
        genericBet.setDescription("This is part of mock test");
        genericBet.setCreatedTime(new Date());
        genericBet.setBetType(BetTypes.HIGESTSCORER);

        Calendar c = new GregorianCalendar();
        c.add(Calendar.DATE, 5);
        Date d = c.getTime();
        genericBet.setClosingTime(d);

        Map<String, String> options = new HashMap<String, String>();
        options.put("OPTIONA", "Kohli");
        options.put("OPTIONB", "Smith");
        options.put("OPTIONC", "Sharma");
        options.put("OPTIOND", "Gayle");
        genericBet.setOptions(options);

        List<GenericBet> genericBetList = new ArrayList<GenericBet>();
        genericBetList.add(genericBet);

        when(genericBetServiceMock.findAll()).thenReturn(genericBetList);

        Assert.assertNotNull("mockMVC cannot be null", mockMvc);
        Assert.assertNotNull("genericBetControllerMock cannot be null", genericBetControllerMock);


        Assert.assertNotNull("contentType cannot be null", contentType);
        mockMvc.perform(get("/bets/")
                .contentType(contentType))
                .andDo(print())
                .andExpect(jsonPath("$[*].createdTime").exists())
                .andExpect(status().isOk());
    }
}
