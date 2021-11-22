package com.revature.revspace.controllers;

import com.revature.revspace.models.Credentials;
import com.revature.revspace.models.Post;
import com.revature.revspace.models.User;
import com.revature.revspace.services.CredentialsService;
import com.revature.revspace.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class PostController
{
    @Autowired
    PostService pos;
//    @Autowired
//    PostService ps;

    @PostMapping(value ="/posts", consumes = "application/json" , produces = "application/json" )
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Post> addPost(@RequestBody Post p)
    {
        return new ResponseEntity<>(pos.add(p), HttpStatus.OK);
    }


    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPosts(){
        return new ResponseEntity<>(pos.getAll(),HttpStatus.OK);
    }


    //Get Post By ID
    @GetMapping("/posts/{id}")
    public Post getPostById(@PathVariable(name = "id") String id)
    {

        int safeId;
        try
        {
            safeId = Integer.parseInt(id);
        }catch (NumberFormatException e)
        {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        Post foundPost = pos.get(safeId);
        if (null != foundPost)
        {
            return foundPost;
        }else
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    //Update Post By ID
    @PutMapping(value = "/posts/{id}", consumes = "application/json")
    public Post updatePost(@PathVariable("id") String id, @RequestBody Post newPost)
    {
        
        int safeId;
        try
        {
            safeId = Integer.parseInt(id);
        }catch (NumberFormatException e)
        {
            safeId = 0;
        }
        newPost.setPostId(safeId);
        return pos.update(newPost);
    }

    //Delete Post By ID
    @DeleteMapping(value = "/posts/{id}")
    public boolean deletePost(@PathVariable("id") String id)
    {

        int safeId;
        try
        {
            safeId = Integer.parseInt(id);
        }catch (NumberFormatException e)
        {
            safeId = 0;
        }
        return pos.delete(safeId);
    }
}
