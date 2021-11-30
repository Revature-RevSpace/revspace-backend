package com.revature.revspace.controllers;

import com.revature.revspace.models.Like;
import com.revature.revspace.models.Post;
import com.revature.revspace.services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class LikeController {

    @Autowired
    LikeService ls;

    @GetMapping(value = "/likes/{id}")
    public Like getLikeById(@PathVariable(name = "id") String id){
        int safeId;
        try{
            safeId = Integer.parseInt(id);
        } catch(NumberFormatException e)
        {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ls.get(safeId);
    }

    @GetMapping(value = "/likes")
    public List<Like> getAllLikes(){
        return ls.getAll();
    }

    @PostMapping(value="/likes", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Like addLike(@RequestBody Like like)
    {
        return ls.add(like);
    }

    //Update Like By ID
    @PutMapping(value = "/likes", consumes = "application/json", produces = "application/json")
    public Like updateLike(@RequestBody Like like)
    {
        return ls.update(like);
    }



    //Delete Post By ID
    @DeleteMapping(value = "/likes/{id}")
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
        return ls.delete(safeId);
    }
}
