package com.revature.revspace.services;

import com.revature.revspace.models.Like;
import com.revature.revspace.models.Post;
import com.revature.revspace.models.User;
import com.revature.revspace.repositories.LikeRepo;
import com.revature.revspace.repositories.PostRepo;
import com.revature.revspace.testutils.ModelGenerators;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes= com.revature.revspace.app.RevSpaceWebServiceApplication.class)
//@TestPropertySource("classpath:application-test.properties")
public class PostServiceTests {
    @Autowired
    PostService ps;

    @MockBean
    PostRepo pr;

    @MockBean
    LikeRepo lr;

    private List<Post> allComments;
    private List<Post> sortedRelatedComments;
    private List<Post> postList;
    private List<Like> likeList;
    private Post parentPost1;

    @BeforeEach
    void setUp(){
        allComments = new ArrayList<>();
        List<Post> firstLevelComments = new ArrayList<>();
        List<Post> secondLevelComments = new ArrayList<>();
        List<Post> unrelatedComments = new ArrayList<>();
        sortedRelatedComments = new ArrayList<>();
        postList = new ArrayList<>();
        likeList = new ArrayList<>();
        parentPost1 = new Post();
        Post parentPost2 = new Post();
        List<User> userList = new ArrayList<>();

        for (int i = 0; i < 5; i++){
            userList.add(ModelGenerators.makeRandomUser(i+1));
        }

        for (int i = 0; i < 5; i++){
            firstLevelComments.add(new Post(i+1,
                    userList.get(i),"body","image",0,
                    true, parentPost1));
        }

        for (int i = 0; i < 2; i++){
            secondLevelComments.add(new Post(i+5,
                    userList.get(i),"body","image",0,
                    true, firstLevelComments.get(i)));
        }

        for (int i = 0; i < 5; i++){
            unrelatedComments.add(new Post(i+7,
                    userList.get(i),"body","image",0,
                    true, parentPost2));
        }

      
        for (int i = 0; i < 2; i++){
            likeList.add(new Like(i+1, userList.get(i+1),parentPost1));

        }

        allComments.addAll(firstLevelComments);
        allComments.addAll(secondLevelComments);
        allComments.addAll(unrelatedComments);

        parentPost1.setPostId(1);
        parentPost1.setCreatorId(userList.get(1));
        parentPost1.setBody("post1Body");
        parentPost1.setImage("post1Image");
        parentPost1.setDate(1637610000);
        parentPost1.setComment(false);
        parentPost1.setParentPost(null);
        postList.add(parentPost1);

        parentPost2.setPostId(2);
        parentPost2.setCreatorId(userList.get(2));
        parentPost2.setBody("post2 Body");
        parentPost2.setImage("post2 Image");
        parentPost2.setDate(1637600000);
        parentPost2.setComment(false);
        parentPost2.setParentPost(null);
        postList.add(parentPost2);
    }

    @Test
    void getRepo(){
        Assertions.assertNotNull(ps.getRepo());
    }

    @Test
    void getIDFor(){
        Assertions.assertNotNull(ps.getIDFor(parentPost1));
    }

    @Test
    void pullPostsListZero(){
        sortedRelatedComments = ps.selectedRelatedComments(parentPost1, allComments);
        Mockito.when(pr.findByCommentFalseOrderByDateDesc()).thenReturn(postList);
        Mockito.when(pr.findByCommentTrueOrderByDateAsc()).thenReturn(allComments);
        Mockito.when(lr.findAll()).thenReturn(likeList);
        List<List<Post>> actual = ps.pullPostsList(0);

        Assertions.assertNotNull(actual);
    }

    @Test
    void pullPostsListNotZero(){
        Mockito.when(pr.findByCommentFalseOrderByDateDesc()).thenReturn(postList);
        Mockito.when(pr.findByCommentTrueOrderByDateAsc()).thenReturn(allComments);
        Mockito.when(lr.findAll()).thenReturn(likeList);
        List<List<Post>> actual = ps.pullPostsList(10);
        List<List<Post>> expected = new ArrayList<>();
        List<Post> list = new ArrayList<>();
        expected.add(list);
        expected.add(list);
        expected.add(list);
        Assertions.assertEquals(expected,actual);
    }

}
