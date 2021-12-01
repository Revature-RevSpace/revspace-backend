package com.revature.revspace.services;

import com.revature.revspace.models.Credentials;
import com.revature.revspace.models.Like;
import com.revature.revspace.models.Post;
import com.revature.revspace.models.User;
import com.revature.revspace.repositories.LikeRepo;
import com.revature.revspace.repositories.PostRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest(classes= com.revature.revspace.app.RevSpaceWebServiceApplication.class)
public class LikeServiceTest {
    @Autowired
    LikeService ls;

    @MockBean
    PostRepo pr;

    @MockBean
    LikeRepo lr;

    @Test
    void getIDFor(){

        User user = new User(1,"abc@email.com", "name","name", 8708779L, 234243234L, "git", "title", "location", "aboutme");
        Credentials credentials = new Credentials(1,user,"password");
        Post post = new Post(2, credentials.getUser(), "body2","image",1234569L,true,null);

        Assertions.assertNotNull(ls.getIDFor(new Like(1, user, post)));
    }
}
