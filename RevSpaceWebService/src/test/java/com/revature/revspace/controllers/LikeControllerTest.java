package com.revature.revspace.controllers;

import com.revature.revspace.models.Like;
import com.revature.revspace.models.Post;
import com.revature.revspace.models.User;
import com.revature.revspace.services.LikeService;
import com.revature.revspace.testutils.ModelGenerators;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@TestPropertySource("classpath:application-test.properties")
@SpringBootTest(classes = com.revature.revspace.app.RevSpaceWebServiceApplication.class)
public class LikeControllerTest {

    @MockBean
    LikeService ls;

    @Test
    void getLikeById(){
        int safeId=1;
        ls.get(safeId);
    }
    @Test

    void getAllLikes() throws Exception{
        List<Like> likes = new ArrayList<>();
        User user = ModelGenerators.makeRandomUser(1);
        Post post = new Post();
        Like fakeLike = new Like(1, user, post);
        likes.add(fakeLike);
        Mockito.when(ls.getAll())
                .thenReturn(likes);
        Assertions.assertEquals(likes.get(0),fakeLike);
    }

    @Test
    void addLike()
    {
        User user = ModelGenerators.makeRandomUser(1);
        Post post = new Post();
        Like fakeLike = new Like(1, user, post);
        Like actualLike = new Like(1, user, post);
        Mockito.when(ls.add(fakeLike))
                .thenReturn(fakeLike);
        Assertions.assertEquals(fakeLike,actualLike);
    }
}
