package com.revature.revspace.controllers;

import com.revature.revspace.models.Post;
import com.revature.revspace.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class PostController {
    @Autowired
    PostService pos;

    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return pos.getAllPosts();
    }
}
