package com.paeez.test.integration;

import com.paeez.test.base.BaseTest;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by Shrikant on 1/5/15.
 */

public class GenericBetControllerTest extends BaseTest {

   @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        //this.genericBetRepository.deleteAll();

    }

    @Test
    public void testGenericBetNotFound() throws Exception {
        mockMvc.perform(get("/bets/")
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGenericBetFindAll() throws Exception {
        mockMvc.perform(get("/bets/")
                .contentType(contentType))
                .andExpect(jsonPath("$[*].createdTime").exists())
                .andExpect(status().isOk());
    }
}
