package com.revature.revspace.repositories;

import com.revature.revspace.app.RevSpaceWebServiceApplication;
import com.revature.revspace.models.User;
import com.revature.revspace.testutils.ModelGenerators;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@TestPropertySource("classpath:application-test.properties")
@SpringBootTest(classes= RevSpaceWebServiceApplication.class)
@Transactional
public class UserRepoTests
{
	@Autowired
	private UserRepo userRepo;

	@Test
	@Rollback
	void findUserByEmailFindsUser()
	{
		// create a user, then try to find it
		User user = ModelGenerators.makeRandomUser();
		User expectedUser = this.userRepo.save(user);
		String email = expectedUser.getEmail();
		User actualUser = this.userRepo.findByEmail(email);
		Assertions.assertEquals(expectedUser, actualUser);
	}

	@Test
	@Rollback
	void findUserByEmailDoesntFindUserIfNoUserWithThatEmailExists()
	{
		// create a user, then delete it, then try to find it (emails are unique)
		User user = ModelGenerators.makeRandomUser();
		user = this.userRepo.save(user);
		int id = user.getUserId();
		String email = user.getEmail();
		this.userRepo.deleteById(id);
		User actualUser = this.userRepo.findByEmail(email);
		Assertions.assertNull(actualUser);
	}

	@Test
	@Rollback
	void findUserByNullEmailDoesntFindUser()
	{
		User actualUser = this.userRepo.findByEmail(null);
		Assertions.assertNull(actualUser);
	}
}
