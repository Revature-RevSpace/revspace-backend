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
import java.util.Objects;

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
        User updatedUser = null;
        try
        {
            if(!Objects.equals(creds.getPassword(), "")
                    && creds.getUser() != null
                    && !Objects.equals(creds.getUser().getEmail(), "")
                    && !Objects.equals(creds.getUser().getFirstName(), "")
                    && !Objects.equals(creds.getUser().getLastName(), "")
                    && us.getUserByEmail(creds.getUser().getEmail()) == null)
            {
                updatedUser = us.add(creds.getUser());
                cs.add(creds);
            }else if(creds.getUser() != null && us.getUserByEmail(creds.getUser().getEmail()) != null)
            {
                throw new ResponseStatusException(HttpStatus.CONFLICT);
            }else
            {
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
            }
        }catch (IllegalArgumentException ignored) {}
        return updatedUser;
    }

    @PutMapping(value = "/users/{id}", consumes = "application/json")
    public User updateUser(@PathVariable("id") String id, @RequestBody User newUser)
    {
        User resultUser;
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
        resultUser = us.update(newUser);
        if (resultUser == null)
        {
            throw new ResponseStatusException
                    (
                        (safeId == 0)?HttpStatus.NOT_FOUND:HttpStatus.UNPROCESSABLE_ENTITY
                    );
        }
        return resultUser;
    }

    @DeleteMapping(value = "/users/{id}")
    public boolean deleteUser(@PathVariable("id") String id)
    {
        boolean idFound;
        //parsing int from string, can(should) be done somewhere else
        int safeId;
        try
        {
            safeId = Integer.parseInt(id);
        }catch (NumberFormatException e)
        {
            safeId = 0;
        }
        idFound = us.delete(safeId);
        if(!idFound)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return true;
    }
}
