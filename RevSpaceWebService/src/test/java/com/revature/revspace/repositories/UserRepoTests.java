package com.revature.revspace.repositories;

import com.revature.revspace.app.RevSpaceWebServiceApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes= RevSpaceWebServiceApplication.class)
@Transactional
public class UserRepoTests
{
	@Test
	void findUserByEmailFindsUser()
	{
	}

	@Test
	void findUserByEmailDoesntFindUserIfNoUserWithThatEmailExists()
	{
	}

	@Test
	void findUserByNullEmailDoesntFindUser()
	{
	}
}
