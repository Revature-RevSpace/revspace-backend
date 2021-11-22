package com.revature.revspace.repositories;

import com.revature.revspace.models.Credentials;
import com.revature.revspace.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CredentialsRepo extends CrudRepository<Credentials, Integer>{
    Credentials findByUserEmail(String email);
    Credentials findByUserUserId(int userId);
}


