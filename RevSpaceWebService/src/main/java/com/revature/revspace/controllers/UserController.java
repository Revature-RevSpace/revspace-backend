package com.revature.revspace.controllers;

import com.revature.revspace.models.Credentials;
import com.revature.revspace.models.User;
import com.revature.revspace.services.CredentialsService;
import com.revature.revspace.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class UserController
{
    @Autowired
    private UserService us;
    @Autowired
    private CredentialsService cs;

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable(name = "id") String id)
    {
        //parsing int from string can(should) be done somewhere else
        int safeId;
        try
        {
            safeId = Integer.parseInt(id);
        }catch (NumberFormatException e)
        {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        User foundUser = us.get(safeId);
        if (null != foundUser)
        {
            return foundUser;
        }else
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value ="/users", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody Credentials creds)
    {
        User updatedUser;
        try
        {
            updatedUser = us.add(creds.getUser());
            cs.add(creds);
        }catch (IllegalArgumentException e)
        {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return updatedUser;
    }

    @PutMapping(value = "/users/{id}", consumes = "application/json")
    public User updateUser(@PathVariable("id") String id, @RequestBody User newUser)
    {
        //parsing int from string, can(should) be done somewhere else
        int safeId;
        try
        {
            safeId = Integer.parseInt(id);
        }catch (NumberFormatException e)
        {
            safeId = 0;
        }
        newUser.setUserId(safeId);
        return us.update(newUser);
    }

    @DeleteMapping(value = "/users/{id}")
    public boolean deleteUser(@PathVariable("id") String id)
    {
        //parsing int from string, can(should) be done somewhere else
        int safeId;
        try
        {
            safeId = Integer.parseInt(id);
        }catch (NumberFormatException e)
        {
            safeId = 0;
        }
        return us.delete(safeId);
    }
}
