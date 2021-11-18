package com.revature.revspace.repositories;

import com.revature.revspace.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Integer>
{
}
