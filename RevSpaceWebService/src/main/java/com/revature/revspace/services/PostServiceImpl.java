package com.revature.revspace.services;

import com.revature.revspace.models.Post;
import com.revature.revspace.repositories.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    PostRepo postRepo;

    //The larger the unix time, the newer the post.

    static List<Post> sortedCurrentPostsList = new ArrayList<>();       // Posts are in descending order
    static List<Post> sortedCurrentCommentsList = new ArrayList<>();   // Comments are in ascending order


    @Override
    public PostRepo getRepo(){
        return postRepo;
    }

    @Override
    public Integer getIDFor(Post value) {
        return null;
    }



}
