package com.revature.revspace.services;

import com.revature.revspace.models.Credentials;
import com.revature.revspace.models.User;
import com.revature.revspace.repositories.CredentialsRepo;

public interface CredentialsService extends CrudService<Credentials, Integer, CredentialsRepo> {

    Credentials getByEmail(String email);
}
