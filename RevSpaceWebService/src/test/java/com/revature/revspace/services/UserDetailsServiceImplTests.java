package com.revature.revspace.services;

import com.revature.revspace.app.RevSpaceWebServiceApplication;
import com.revature.revspace.models.Credentials;
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
import org.springframework.test.context.TestPropertySource;

@TestPropertySource("classpath:application-test.properties")
@SpringBootTest(classes= RevSpaceWebServiceApplication.class)
public class UserDetailsServiceImplTests
{
	@Autowired
	UserDetailsService userDetailsService;

	@MockBean
	UserService userService;

	@MockBean
	CredentialsService credentialsService;

	@Test
	void loadUserByUsernameLoadsUser()
	{
		User mockUser = ModelGenerators.makeRandomUser(1);
		Credentials mockCredentials = ModelGenerators.makeRandomCredentials(1, mockUser);
		String email = mockUser.getEmail();
		String[] expectedRoles = {"ROLE_USER"};
		Mockito.when(userService.getUserByEmail(email))
			.thenReturn(mockUser);
		Mockito.when(credentialsService.getByEmail(email))
			.thenReturn(mockCredentials);
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
	void loadMissingUserThrowsException()
	{
		String email = ModelGenerators.makeRandomAlphaString(5,10);
		Mockito.when(userService.getUserByEmail(email))
			.thenReturn(null);
		Assertions.assertThrows(UsernameNotFoundException.class, ()->this.userDetailsService.loadUserByUsername(email));
	}
}
