package com.revature.revspace.services;

import com.revature.revspace.app.RevSpaceWebServiceApplication;
import com.revature.revspace.models.User;
import com.revature.revspace.repositories.UserRepo;
import com.revature.revspace.testutils.ModelGenerators;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes= RevSpaceWebServiceApplication.class)
public class UserServiceImplTests
{
	@Autowired
	UserService userService;

	@MockBean
	UserRepo repo;

	@Test
	void getUserByEmailGetsUser()
	{
		User expectedUser = ModelGenerators.makeRandomUser(1);
		String email = expectedUser.getEmail();
		Mockito.when(this.repo.findByEmail(email)).thenReturn(expectedUser);
		User actualUser = this.userService.getUserByEmail(email);
		Assertions.assertEquals(expectedUser, actualUser);
	}

	@Test
	void getUserByEmailDoesntGetUserIfNoUserWithEmailExists()
	{
		String email = ModelGenerators.makeRandomAlphaString(5, 10);
		Mockito.when(this.repo.findByEmail(email)).thenReturn(null);
		User actualUser = this.userService.getUserByEmail(email);
		Assertions.assertNull(actualUser);
	}

	@Test
	void getUserByNullEmailDoesntGetUser()
	{
		Mockito.when(this.repo.findByEmail(null)).thenReturn(null);
		User actualUser = this.userService.getUserByEmail(null);
		Assertions.assertNull(actualUser);
	}
}