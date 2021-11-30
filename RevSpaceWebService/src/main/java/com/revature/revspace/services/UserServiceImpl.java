package com.revature.revspace.services;

import com.revature.revspace.models.User;
import com.revature.revspace.repositories.UserRepo;
import com.revature.revspace.utils.LoggedInUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepo ur;

    @Autowired
    private LoggedInUser loggedInUser;

    @Override
    public UserRepo getRepo()
    {
        return ur;
    }

    @Override
    public Integer getIDFor(User value)
    {
        return value.getUserId();
    }

    @Override
    public User getUserByEmail(String email)
    {
        return this.ur.findByEmail(email);
    }

    /**
     * @return Currently logged-in user. Returns null if used outside an HTTP request scope.
     */
    @Override
    public User getLoggedInUser()
    {
        return this.loggedInUser.getUser();
    }
}
