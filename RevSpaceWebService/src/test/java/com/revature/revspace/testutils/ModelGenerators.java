package com.revature.revspace.testutils;

import com.revature.revspace.models.User;

import java.util.Random;

public class ModelGenerators
{
	private static Random RANDOM = new Random(System.currentTimeMillis());

	/**
	 * @return an idless user with random info
	 */
	public static User makeRandomUser()
	{
		String email = makeRandomAlphaString(2,10) + "@revature.net";
		String firstName = makeRandomAlphaString(2,20);
		String lastName = makeRandomAlphaString(2,20);
		long birthday = (1990L*365L*24L*60L*60L*1000L) + (RANDOM.nextLong() % 1000L)*365L*24L*60L*60L*1000L;
		long revatureJoinDate = (2010L*365L*24L*60L*60L*1000L) + (RANDOM.nextLong() % 1000L)*365L*24L*60L*60L*1000L;
		String githubUserName = makeRandomAlphaString(5, 20);
		String title = makeRandomAlphaString(3, 20);
		String location = makeRandomAlphaString(3, 20);
		String aboutMe = makeRandomAlphaString(3, 50);

		return new User(email, firstName, lastName, birthday, revatureJoinDate, githubUserName, title, location, aboutMe);
	}

	/**
	 * @param id ID to set to the user
	 * @return User with random info and specified ID
	 */
	public static User makeRandomUser(int id)
	{
		User user = makeRandomUser();
		user.setUserId(id);
		return user;
	}

	/**
	 * Generates a random string of alphabetic characters.
	 * @param minLength Minimum length of the string.
	 * @param maxLength Maximum length of the string.
	 * @return A random string of alphabetic characters with
	 * size in range [minLength, maxLength] inclusive
	 */
	public static String makeRandomAlphaString(int minLength, int maxLength)
	{
		if (minLength > maxLength)
			throw new IllegalArgumentException("minLength cannot be greater than maxLength");
		int length = minLength + RANDOM.nextInt(1 + maxLength-minLength);
		StringBuilder builder = new StringBuilder();
		for (int i=0; i<length; i++)
		{
			char c = (char)(97 + RANDOM.nextInt(26));
			builder.append(c);
		}
		return builder.toString();
	}
}
