package com.revature.revspace.services;

import com.revature.revspace.models.Credentials;
import com.revature.revspace.models.User;
import com.revature.revspace.repositories.CredentialsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CredentialsServiceImpl implements CredentialsService{

    @Autowired
    CredentialsRepo credentialsRepo;

    @Override
    public CredentialsRepo getRepo() {
        return credentialsRepo;
    }

    @Override
    public User getIDFor(Credentials value) {
        return value.getUser();
    }
}
