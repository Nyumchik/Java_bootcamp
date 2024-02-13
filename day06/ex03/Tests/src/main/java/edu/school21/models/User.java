package edu.school21.models;

public class User {
	private Long id;
	private String login;
	private String password;
	private boolean authenticated;

	public User(String login, String password, boolean authenticated) {
		this.login = login;
		this.password = password;
		this.authenticated = authenticated;
	}

	
	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public boolean getAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}
}
