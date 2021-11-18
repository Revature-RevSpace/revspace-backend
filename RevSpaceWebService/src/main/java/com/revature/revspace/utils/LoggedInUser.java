package com.revature.revspace.utils;

import com.revature.revspace.models.User;

/**
 * We need to represent a nullable user model.
 * We also need this representation to be a bean.
 * This will represent the currently-logged-in user (if any).
 *
 * Beans can't be null, so we can't just use a nullable user as the bean.
 * Using generic classes as beans can cause ambiguity problems, so using an
 * Optional of the User as the bean might not be desirable.
 * So we'll just make this class that holds a nullable user.
 */
public class LoggedInUser
{
	private User user;

	/**
	 * @param user Can be null
	 */
	public LoggedInUser(User user)
	{
		this.user = user;
	}

	/**
	 * @return The currently-logged in user. Null if user is not logged in.
	 */
	public User getUser()
	{
		return this.user;
	}
}
