package com.revature.revspace.repositories;

import com.revature.revspace.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Integer>
{
	/**
	 * Finds a user who has this email, if any.
	 * @param email Email string to search users for.
	 * @return User with that email if it exists.
	 * Returns null if no user exists with that email.
	 */
	public User findByEmail(String email);
}
