package com.revature.revspace.services;

import com.revature.revspace.models.Post;
import com.revature.revspace.models.User;
import com.revature.revspace.repositories.PostRepo;
import com.revature.revspace.testutils.ModelGenerators;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = com.revature.revspace.app.RevSpaceWebServiceApplication.class)

public class PostServiceTests {
    @Autowired
    PostService ps;

//    @MockBean
//    PostRepo pr;

    @Test
    void selectedRelatedComments(){
        List<Post> allComments = new ArrayList<>();

        Post parentPost = new Post(0,
                ModelGenerators.makeRandomUser(1),"body","image",0,
                false, null);

        List<Post> firstLevelComments = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            firstLevelComments.add(new Post(i+1,
                    ModelGenerators.makeRandomUser(i+1),"body","image",0,
                    true, parentPost));
        }

        List<Post> secondLevelComments = new ArrayList<>();
        for (int i = 0; i < 2; i++){
            secondLevelComments.add(new Post(i+5,
                    ModelGenerators.makeRandomUser(i+1),"body","image",0,
                    true, firstLevelComments.get(i)));
        }

        List<Post> unrelatedComments = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            unrelatedComments.add(new Post(i+7,
                    ModelGenerators.makeRandomUser(i+1),"body","image",0,
                    true, null));
        }

        allComments.addAll(firstLevelComments);
        allComments.addAll(secondLevelComments);
        allComments.addAll(unrelatedComments);

        List<Post> expected = new ArrayList<>();
        expected.addAll(firstLevelComments);
        expected.addAll(secondLevelComments);
        List<Post> actual = ps.selectedRelatedComments(parentPost, allComments);
        Assertions.assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));
    }
}
