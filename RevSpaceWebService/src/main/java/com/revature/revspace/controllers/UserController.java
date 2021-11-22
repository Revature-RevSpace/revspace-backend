package com.revature.revspace.controllers;

import com.revature.revspace.models.Credentials;
import com.revature.revspace.models.User;
import com.revature.revspace.services.CredentialsService;
import com.revature.revspace.services.UserService;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
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


    @GetMapping("/login")
    public User getCurrentUser()
    {
        return this.us.getLoggedInUser();
    }

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

    /**
     * Adds a given user through a surrounding credentials object
     * gives a 409 for duplicate username, 422 for incomplete input
     * @param creds a credentials object represented in JSON
     * @return the user to be added without updated id
     */
    @PostMapping(value ="/users", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody Credentials creds)
    {
        User updatedUser;
        try
        {
            if(creds.getUser() != null && us.getUserByEmail(creds.getUser().getEmail()) == null)
            {
                updatedUser = us.add(creds.getUser());
                cs.add(creds);
            }else if(creds.getUser() == null)
            {
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
            }else
            {
                throw new ResponseStatusException(HttpStatus.CONFLICT);
            }
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
