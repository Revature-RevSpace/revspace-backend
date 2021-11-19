package com.revature.services;

import com.revature.revspace.app.RevSpaceWebServiceApplication;
import com.revature.revspace.models.Like;
import com.revature.revspace.models.Post;
import com.revature.revspace.models.User;
import com.revature.revspace.repositories.PostRepo;
import com.revature.revspace.services.PostService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = RevSpaceWebServiceApplication.class)
public class PostServiceTest {
    @Autowired
    PostService pos;

    @MockBean
    PostRepo mir;




//    @Test
//    void getAllPosts() {
//        User u1 = new User(1,"email","email","name",null,null,"githubuser","title","location","aboutme");
//        Post p1 = new Post(1,u1,"body","image",1235458656,false, null);
//        Like l1 = new Like(1,u1,p1);
//
//        List<Post> expected = new ArrayList<>();
//
//        expected.add(p1);
//
//        Mockito.when(mir.findAll()).thenReturn(expected);
//
//        List<Post> actual = pos.getAllPosts();
//
//        Assertions.assertEquals(actual, expected);
//    }
}
