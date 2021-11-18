package com.revature.revspace.controllers;

import com.revature.revspace.app.RevSpaceWebServiceApplication;
import com.revature.revspace.models.User;
import com.revature.revspace.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@AutoConfigureMockMvc
@SpringBootTest(classes = RevSpaceWebServiceApplication.class)
public class UserControllerTests
{
	@MockBean
	private UserService service;

	@Autowired
	private MockMvc mvc;

	@Test
	void getUser() throws Exception
	{
		// TODO write test
	}

	@Test
	void getUsers() throws Exception
	{
		List<User> users = new ArrayList<>();
		Mockito.when(service.getAll())
			.thenReturn(users);
		ResultActions actions = mvc.perform(MockMvcRequestBuilders.get("/users"));
		actions.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void addUser() throws Exception
	{
		User user = null;

		Mockito.when(service.add(user))
			.thenReturn(user);
		ResultActions actions = mvc.perform(MockMvcRequestBuilders.post("/users")
			.contentType("application/json")
			.content("{}"));
		actions.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	void updateUser() throws Exception
	{
		User user = null;

		Mockito.when(service.add(new User()))
			.thenReturn(user);
		ResultActions actions = mvc.perform(MockMvcRequestBuilders.put("/users/0")
			.contentType("application/json")
			.content("{}"));
		actions.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void deleteUser() throws Exception
	{
		User user = null;

		Mockito.when(service.delete(0))
			.thenReturn(true);
		ResultActions actions = mvc.perform(MockMvcRequestBuilders.delete("/users/0")
			.contentType("application/json")
			.content("{}"));
		actions.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
