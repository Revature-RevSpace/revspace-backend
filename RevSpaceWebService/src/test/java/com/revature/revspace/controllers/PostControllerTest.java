package com.revature.revspace.controllers;


import com.revature.revspace.services.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest(classes = com.revature.revspace.app.RevSpaceWebServiceApplication.class)
@TestPropertySource("classpath:application.properties")
public class PostControllerTest {
    @MockBean
    PostService pos;
    
    @Autowired
    MockMvc mvc;

    @Test
    void getAllPosts() throws Exception {
        ResultActions ra = mvc.perform(MockMvcRequestBuilders.get("/posts"));
        ra.andExpect(MockMvcResultMatchers.status().isOk());
    }
}
