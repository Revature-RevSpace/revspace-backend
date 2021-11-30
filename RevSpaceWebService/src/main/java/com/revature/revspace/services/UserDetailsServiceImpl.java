package com.revature.revspace.services;

import com.revature.revspace.models.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * This service is responsible for looking up user login information ("user details")
 * based on the usernames provided with API requests.
 * The security service will use this to check if the username and password
 * provided in API requests match the user details provided by this user
 * details service.
 */
public class UserDetailsServiceImpl implements UserDetailsService
{
	@Autowired
	private UserService userService;

	@Autowired
	private CredentialsService credentialsService;

	@Autowired
	PasswordEncoder passwordEncoder;

	/**
	 * Locates the user based on the username. In the actual implementation, the search
	 * may possibly be case sensitive, or case insensitive depending on how the
	 * implementation instance is configured. In this case, the <code>UserDetails</code>
	 * object that comes back may have a username that is of a different case than what
	 * was actually requested..
	 *
	 * @param userEmail the username identifying the user whose data is required.
	 * @return a fully populated user record (never <code>null</code>)
	 * @throws UsernameNotFoundException if the user could not be found or the user has no
	 *                                   GrantedAuthority
	 */
	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException
	{
		com.revature.revspace.models.User user = this.userService.getUserByEmail(userEmail);
		if (user == null)
			throw new UsernameNotFoundException("No user found with email " + userEmail);
		List<String> roles = new ArrayList<>();
		roles.add("USER");
		Credentials credentials = this.credentialsService.getByEmail(userEmail);
		return org.springframework.security.core.userdetails.User.builder()
			.username(user.getEmail())
			.password(credentials.getPassword())
			.passwordEncoder(passwordEncoder::encode)
			.roles(roles.toArray(new String[roles.size()]))
			.build();
	}
}
