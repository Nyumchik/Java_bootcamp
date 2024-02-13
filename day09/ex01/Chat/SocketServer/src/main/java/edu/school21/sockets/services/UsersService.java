package edu.school21.sockets.services;

import edu.school21.sockets.models.User;

public interface UsersService {
	void signUp(User user);
	boolean signIn(User user);
	void saveMessage(String username, String message);
}
