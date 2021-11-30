package com.revature.revspace.services;

import com.revature.revspace.models.Credentials;
import com.revature.revspace.models.User;
import com.revature.revspace.repositories.CredentialsRepo;

public interface CredentialsService extends CrudService<Credentials, Integer, CredentialsRepo> {

    Credentials getByEmail(String email);

    /**
     * gets credentialsId by userId
     * @param id the userId to search for
     * @return credentialsId of result if found, 0 if not found
     */
    Integer getIdByUserId(int id);
}
