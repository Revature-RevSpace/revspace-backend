package com.revature.revspace.models;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name="credentials")
public class Credentials{

	@Id
	@Column(name = "credentials_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int credentialsId;

	@OneToOne
	@JoinColumn(name="user_id", referencedColumnName = "user_id", updatable=false, nullable=false, unique=true)
	private User user;

	@Column(name="password", nullable=false, length=64)
	private String password;

	public Credentials() {
	}

	public Credentials(User user, String password) {
		this.user = user;
		this.password = password;
	}

	public Credentials(int credentialsId, User user, String password) {
		this.credentialsId = credentialsId;
		this.user = user;
		this.password = password;
	}

	public int getCredentialsId() {
		return credentialsId;
	}

	public void setCredentialsId(int credentialsId) {
		this.credentialsId = credentialsId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Credentials that = (Credentials) o;
		return getCredentialsId() == that.getCredentialsId() && Objects.equals(getUser(), that.getUser()) && Objects.equals(getPassword(), that.getPassword());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getCredentialsId(), getUser(), getPassword());
	}

	@Override
	public String toString() {
		return "Credentials{" +
				"credentialsId=" + credentialsId +
				", user=" + user +
				", password='" + password + '\'' +
				'}';
	}
}
