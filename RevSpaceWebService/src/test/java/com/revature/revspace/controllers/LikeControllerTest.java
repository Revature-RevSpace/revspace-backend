package com.revature.revspace.controllers;

import com.google.gson.Gson;
import com.revature.revspace.models.Credentials;
import com.revature.revspace.models.Like;
import com.revature.revspace.models.Post;
import com.revature.revspace.models.User;
import com.revature.revspace.repositories.CredentialsRepo;
import com.revature.revspace.services.LikeService;
import com.revature.revspace.services.PostService;
import com.revature.revspace.testutils.ModelGenerators;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@AutoConfigureMockMvc
@TestPropertySource("classpath:application.properties")
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest(classes = com.revature.revspace.app.RevSpaceWebServiceApplication.class)
public class LikeControllerTest {

    private static final String TEST_EMAIL = "testemail@revature.net";

    @MockBean
    LikeService ls;

    @Autowired
    MockMvc mvc;

    @Autowired
    Gson gson;

    @MockBean
    CredentialsRepo credentialsRepo;

    @MockBean
    PostService service;

    @Test
    @WithMockUser(username=TEST_EMAIL)
    void getLikeById() throws Exception{
//        int safeId=1;
//        ls.get(safeId);

        User user = new User(1,"abc@email.com", "name","name", 8708779L, 234243234L, "git", "title", "location", "aboutme");
        Credentials credentials = new Credentials(1,user,"password");
        Mockito.when(credentialsRepo.findByUserEmail(user.getEmail())).thenReturn(credentials);
        Post post = new Post(1, credentials.getUser(), "body","image",1234569L,true,null);
        Like fakeLike = new Like(1, user, post);
        Mockito.when(service.get(1))
                .thenReturn(post);
        ResultActions actions = mvc.perform(MockMvcRequestBuilders.get("/likes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(fakeLike)));
        actions.andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    @WithMockUser(username=TEST_EMAIL)
    void getLikeByIdWithoutInt() throws Exception{
//        int safeId=1;
//        ls.get(safeId);

        User user = new User(1,"abc@email.com", "name","name", 8708779L, 234243234L, "git", "title", "location", "aboutme");
        Credentials credentials = new Credentials(1,user,"password");
        Mockito.when(credentialsRepo.findByUserEmail(user.getEmail())).thenReturn(credentials);
        Post post = new Post(1, credentials.getUser(), "body","image",1234569L,true,null);
        Like fakeLike = new Like(1, user, post);
        Mockito.when(service.get(1))
                .thenReturn(post);
        ResultActions actions = mvc.perform(MockMvcRequestBuilders.get("/likes/{id}","a")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(fakeLike)));
        actions.andExpect(MockMvcResultMatchers.status().is(422));
    }





    @Test
    @WithMockUser(username=TEST_EMAIL)
    void getAllLikes() throws Exception{
        List<Like> likes = new ArrayList<>();
        User user = new User(1,"abc@email.com", "name","name", 8708779L, 234243234L, "git", "title", "location", "aboutme");
        Credentials credentials = new Credentials(1,user,"password");
        Mockito.when(credentialsRepo.findByUserEmail(user.getEmail())).thenReturn(credentials);
        Post post = new Post(1, credentials.getUser(), "body","image",1234569L,true,null);
        Like fakeLike = new Like(1, user, post);
        Mockito.when(service.get(1)).thenReturn(post);
        ResultActions actions = mvc.perform(MockMvcRequestBuilders.get("/likes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(fakeLike)));
        actions.andExpect(MockMvcResultMatchers.status().isOk());


    }

    @Test
    @WithMockUser(username=TEST_EMAIL)
    void addLike() throws Exception{
//        User user = ModelGenerators.makeRandomUser(1);
//        Post post = new Post();
//        Like fakeLike = new Like(1, user, post);
//        Like actualLike = new Like(1, user, post);
//        Mockito.when(ls.add(fakeLike))
//                .thenReturn(fakeLike);
//        Assertions.assertEquals(fakeLike,actualLike);


        User user = new User("abc@email.com", "name","name", 8708779L, 234243234L, "git", "title", "location", "aboutme");
        Credentials credentials = new Credentials(user,"password");
        Mockito.when(credentialsRepo.findByUserEmail(user.getEmail())).thenReturn(credentials);
        Post post = new Post(credentials.getUser(), "body","image",1234569L,true,null);
        Like fakeLike = new Like(1, user, post);
        Mockito.when(service.add(post))
                .thenReturn(post);

        ResultActions actions = mvc.perform(MockMvcRequestBuilders.post("/likes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(fakeLike)));
        actions.andExpect(MockMvcResultMatchers.status().isCreated());
    }


    @Test
    @WithMockUser(username=TEST_EMAIL)
    void deleteLike() throws Exception{
//        int safeId=1;
//        ls.get(safeId);

        User user = new User(1,"abc@email.com", "name","name", 8708779L, 234243234L, "git", "title", "location", "aboutme");
        Credentials credentials = new Credentials(1,user,"password");
        Mockito.when(credentialsRepo.findByUserEmail(user.getEmail())).thenReturn(credentials);
        Post post = new Post(1, credentials.getUser(), "body","image",1234569L,true,null);
        Like fakeLike = new Like(1, user, post);
        Mockito.when(service.get(1))
                .thenReturn(post);
        ResultActions actions = mvc.perform(MockMvcRequestBuilders.delete("/likes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(fakeLike)));
        actions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username=TEST_EMAIL)
    void updateLikes() throws Exception{
        List<Like> likes = new ArrayList<>();
        User user = new User(1,"abc@email.com", "name","name", 8708779L, 234243234L, "git", "title", "location", "aboutme");
        Credentials credentials = new Credentials(1,user,"password");
        Mockito.when(credentialsRepo.findByUserEmail(user.getEmail())).thenReturn(credentials);
        Post post = new Post(1, credentials.getUser(), "body","image",1234569L,true,null);
        Post post2 = new Post(2, credentials.getUser(), "body2","image",1234569L,true,null);

        Like fakeLike = new Like(2, user, post);
        Mockito.when(service.get(2)).thenReturn(post);
        ResultActions actions = mvc.perform(MockMvcRequestBuilders.put("/likes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(fakeLike)));
        actions.andExpect(MockMvcResultMatchers.status().isOk());


    }


}
