package com.revature.revspace.services;

import com.revature.revspace.app.RevSpaceWebServiceApplication;
import com.revature.revspace.models.Like;
import com.revature.revspace.models.User;
import com.revature.revspace.repositories.LikeRepo;
import com.revature.revspace.repositories.UserRepo;
import com.revature.revspace.testutils.ModelGenerators;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

/**
 * We'll test this by testing the CRUD methods for a service that uses this
 */
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest(classes= RevSpaceWebServiceApplication.class)
public class CrudServiceTests
{
	@Autowired
	UserService service;

	@Autowired
	LikeService ls;

	@MockBean
	LikeRepo lr;

	@MockBean
	UserRepo repo;

	@Test
	void addUser()
	{
		User user = ModelGenerators.makeRandomUser();
		int oldId = user.getUserId();
		int newId = 1;
		User expectedUser = new User(
			newId,
			user.getEmail(),
			user.getFirstName(),
			user.getLastName(),
			user.getBirthday(),
			user.getRevatureJoinDate(),
			user.getGithubUsername(),
			user.getTitle(),
			user.getLocation(),
			user.getAboutMe()
		);
		Mockito.when(this.repo.save(user)).thenReturn(expectedUser);
		User actualUser = this.service.add(user);
		Assertions.assertEquals(expectedUser, actualUser);
		Assertions.assertNotEquals(oldId, actualUser.getUserId());
	}

	@Test
	void getUser()
	{
		int id = 1;
		User expectedUser = ModelGenerators.makeRandomUser(id);
		Optional<User> optionalUser = Optional.of(expectedUser);
		Mockito.when(this.repo.findById(id)).thenReturn(optionalUser);
		User actualUser = this.service.get(id);
		Assertions.assertEquals(expectedUser, actualUser);
	}

	@Test
	void getUserDoesntGetUserIfIdDoesNotExist()
	{
		int id = 1;
		Mockito.when(this.repo.findById(id)).thenReturn(null);
		User actualUser = null;
		Assertions.assertNull(actualUser);
	}

	@Test
	void getAllUsers()
	{
		User userA = ModelGenerators.makeRandomUser(1);
		User userB = ModelGenerators.makeRandomUser(2);
		User userC = ModelGenerators.makeRandomUser(3);
		List<User> expectedUsers = Lists.list(userA, userB, userC);
		Mockito.when(this.repo.findAll()).thenReturn(expectedUsers);
		List<User> actualUsers = this.service.getAll();
		Assertions.assertEquals(expectedUsers, actualUsers);
	}

	@Test
	void updateUser()
	{
		int id = 1;
		User user = ModelGenerators.makeRandomUser(id);
		String oldEmail = user.getEmail();
		String newEmail = "a" + oldEmail;
		user.setEmail(newEmail);
		Mockito.when(repo.save(user)).thenReturn(user);
		Mockito.when(repo.findById(id)).thenReturn(Optional.of(user));
		User actualUser = this.service.update(user);
		Assertions.assertEquals(user, actualUser);
		Assertions.assertEquals(newEmail, actualUser.getEmail());
	}

	@Test
	void updateUserDoesntUpdateUserIfIdDoesNotExist()
	{
		int id = 1;
		Mockito.when(repo.findById(id)).thenReturn(Optional.empty());
		User user = ModelGenerators.makeRandomUser(id);
		User actualUser = this.service.update(user);
		Assertions.assertNull(actualUser);
	}

	@Test
	void updateNullUserDoesntUpdateUser()
	{
		Assertions.assertNull(this.service.update(null));
	}

	@Test
	void deleteUser()
	{
		int id = 1;
		User user = ModelGenerators.makeRandomUser();
		Optional<User> optionalUser = Optional.of(user);
		Mockito.when(this.repo.findById(id)).thenReturn(optionalUser);
		Mockito.doNothing().when(this.repo).deleteById(id);
		boolean deleted = this.service.delete(id);
		Assertions.assertTrue(deleted);
	}

	@Test
	void deleteUserDoesntDeleteUserIfIdDoesNotExist()
	{
		int id = 1;
		User user = ModelGenerators.makeRandomUser();
		Optional<User> optionalUser = Optional.of(user);
		Mockito.when(this.repo.findById(id)).thenReturn(Optional.empty());
		Mockito.doThrow(IllegalArgumentException.class)
			.when(this.repo).deleteById(id);
		boolean deleted = this.service.delete(id);
		Assertions.assertFalse(deleted);
	}

	@Test
	void deleteNullUserIdDoesntUpdateUser()
	{
		Mockito.doThrow(IllegalArgumentException.class)
			.when(this.repo).deleteById(null);
		Assertions.assertFalse(this.service.delete(null));
	}

	@Test
	void addLike()
	{
		int id = 1;
		Like like = new Like();
		Like expectedLike = new Like();
		expectedLike.setLikeId(id);
		Mockito.when(this.lr.save(like)).thenReturn(expectedLike);
		Like actualLike = ls.add(like);
		Assertions.assertEquals(expectedLike, actualLike);
	}

	@Test
	void getLike()
	{
		int id = 1;
		Like expectedLike = new Like();
		expectedLike.setLikeId(id);
		Optional<Like> optionalLike = Optional.of(expectedLike);
		Mockito.when(this.lr.findById(id)).thenReturn(optionalLike);
		Like actualLike = this.ls.get(id);
		Assertions.assertEquals(id, actualLike.getLikeId());
	}
}