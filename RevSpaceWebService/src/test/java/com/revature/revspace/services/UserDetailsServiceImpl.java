package com.revature.revspace.services;

import com.revature.revspace.app.RevSpaceWebServiceApplication;
import com.revature.revspace.models.User;
import com.revature.revspace.testutils.ModelGenerators;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@SpringBootTest(classes= RevSpaceWebServiceApplication.class)
public class UserDetailsServiceImpl
{
	@Autowired
	UserDetailsService userDetailsService;

	@MockBean
	UserService userService;

	@Test
	void loadUserByUsernameLoadsUser()
	{
		User mockUser = ModelGenerators.makeRandomUser(1);
		String email = mockUser.getEmail();
		String[] expectedRoles = {"ROLE_USER"};
		Mockito.when(userService.getEmployeeByUsername(email))
			.thenReturn(mockUser);
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
		Assertions.assertEquals(email, userDetails.getUsername());
		for (String role : expectedRoles)
		{
			Assertions.assertTrue(userDetails.getAuthorities()
				.stream()
				.map(GrantedAuthority::getAuthority)
				.anyMatch(role::equals));
		}
	}

	@Test
	void loadManagerByUsernameLoadsManager()
	{
		int id = 1;
		String username = "stevet";
		Employee mockEmployee = new Employee(id, "Steve Testperson", username, "password", 0);
		String[] expectedRoles = {"ROLE_USER", "ROLE_MANAGER"};
		Mockito.when(userService.getEmployeeByUsername(username))
			.thenReturn(mockEmployee);
		Mockito.when(userService.isEmployeeManager(id))
			.thenReturn(true);
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
		Assertions.assertEquals(username, userDetails.getUsername());
		for (String role : expectedRoles)
		{
			Assertions.assertTrue(userDetails.getAuthorities()
				.stream()
				.map(GrantedAuthority::getAuthority)
				.anyMatch(role::equals));
		}
	}

	@Test
	void loadMissingUserThrowsException()
	{
		String username = "stevet";
		Mockito.when(userService.getEmployeeByUsername(username))
			.thenReturn(null);
		Assertions.assertThrows(UsernameNotFoundException.class, ()->this.userDetailsService.loadUserByUsername(username));
	}
}
