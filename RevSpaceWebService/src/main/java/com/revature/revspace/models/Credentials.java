package com.revature.revspace.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="credentials")
public class Credentials implements Serializable
{
	@Id
	@OneToOne
	@JoinColumn(name="user_id", referencedColumnName = "user_id", updatable=false, nullable=false, unique=true)
	private User user;

	@Column(name="password", nullable=false, length=64)
	private String password;

	public Credentials()
	{
		this(null, "");
	}

	public Credentials(String password)
	{
		this(null, password);
	}

	public Credentials(User user, String password)
	{
		this.user = user;
		this.password = password;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Credentials that = (Credentials) o;
		return Objects.equals(getUser(), that.getUser()) && Objects.equals(getPassword(), that.getPassword());
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(getUser(), getPassword());
	}

	@Override
	public String toString()
	{
		return "Credentials{" +
			"user=" + user +
			", password='" + password + '\'' +
			'}';
	}
}
