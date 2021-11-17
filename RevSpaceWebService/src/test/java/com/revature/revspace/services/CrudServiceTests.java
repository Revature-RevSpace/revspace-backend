package com.revature.revspace.services;

import com.revature.revspace.app.RevSpaceWebServiceApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

/**
 * We'll test this by testing the CRUD methods for a service that uses this
 */
@SpringBootTest(classes= RevSpaceWebServiceApplication.class)
public class CrudServiceTests
{

	@Test
	void addUser()
	{
		// TODO write test
	}

	@Test
	void getUser()
	{
		// TODO write test
	}

	@Test
	void getUserDoesntGetUserIfIdDoesNotExist()
	{
		// TODO write test
	}

	@Test
	void getAllUsers()
	{
		// TODO write test
	}

	@Test
	void updateUser()
	{
		// TODO write test
	}

	@Test
	void updateUserDoesntUpdateUserIfIdDoesNotExist()
	{
		// TODO write test
	}

	@Test
	void updateNullUserDoesntUpdateUser()
	{
		// TODO write test
	}

	@Test
	void deleteUser()
	{
		// TODO write test
	}

	@Test
	void deleteUserDoesntDeleteUserIfIdDoesNotExist()
	{
		// TODO write test
	}

	@Test
	void deleteNullUserIdDoesntUpdateUser()
	{
		// TODO write test
	}
}