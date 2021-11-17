package com.revature.revspace.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name="users")
public class User
{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="user_id", updatable=false, nullable = false, unique=true)
	private int userId;

	@Column(name="email", length=100, nullable=false, unique=true)
	private String email;

	@Column(name="name", length=100, nullable = false)
	private String name;

	@Column(name="birthday")
	private Long birthday;

	@Column(name="revature_join_date")
	private Long revatureJoinDate;

	@Column(name="github_username", length=50, nullable=false)
	private String githubUsername;

	@Column(name="title", length=100, nullable=false)
	private String title;

	@Column(name="location", length=500, nullable=false)
	private String location;

	@Column(name="aboutme", length=1000, nullable = false)
	private String aboutMe;

	public User()
	{
		this("", "", null, null, "", "", "", "");
	}

	public User(String email, String name, Long birthday, Long revatureJoinDate, String githubUsername, String title, String location, String aboutMe)
	{
		this(0, email, name, birthday, revatureJoinDate, githubUsername, title, location, aboutMe);
	}

	public User(int userId, String email, String name, Long birthday, Long revatureJoinDate, String githubUsername, String title, String location, String aboutMe)
	{
		this.userId = userId;
		this.email = email;
		this.name = name;
		this.birthday = birthday;
		this.revatureJoinDate = revatureJoinDate;
		this.githubUsername = githubUsername;
		this.title = title;
		this.location = location;
		this.aboutMe = aboutMe;
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Long getBirthday()
	{
		return birthday;
	}

	public void setBirthday(Long birthday)
	{
		this.birthday = birthday;
	}

	public Long getRevatureJoinDate()
	{
		return revatureJoinDate;
	}

	public void setRevatureJoinDate(Long revatureJoinDate)
	{
		this.revatureJoinDate = revatureJoinDate;
	}

	public String getGithubUsername()
	{
		return githubUsername;
	}

	public void setGithubUsername(String githubUsername)
	{
		this.githubUsername = githubUsername;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getLocation()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}

	public String getAboutMe()
	{
		return aboutMe;
	}

	public void setAboutMe(String aboutMe)
	{
		this.aboutMe = aboutMe;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return userId == user.userId && Objects.equals(email, user.email) && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday) && Objects.equals(revatureJoinDate, user.revatureJoinDate) && Objects.equals(githubUsername, user.githubUsername) && Objects.equals(title, user.title) && Objects.equals(location, user.location) && Objects.equals(aboutMe, user.aboutMe);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(userId, email, name, birthday, revatureJoinDate, githubUsername, title, location, aboutMe);
	}

	@Override
	public String toString()
	{
		return "User{" +
			"userId=" + userId +
			", email='" + email + '\'' +
			", name='" + name + '\'' +
			", birthday=" + birthday +
			", revatureJoinDate=" + revatureJoinDate +
			", githubUsername='" + githubUsername + '\'' +
			", title='" + title + '\'' +
			", location='" + location + '\'' +
			", aboutMe='" + aboutMe + '\'' +
			'}';
	}
}
