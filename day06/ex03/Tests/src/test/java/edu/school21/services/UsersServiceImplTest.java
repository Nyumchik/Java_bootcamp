package edu.school21.services;

import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import edu.school21.services.UsersServiceImpl;
import edu.school21.exceptions.EntityNotFoundException;
import edu.school21.exceptions.AlreadyAuthenticatedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.beans.Transient;

import org.junit.jupiter.api.Assertions;

public class UsersServiceImplTest {
	@Mock
	private UsersRepository usersRepository;
	@InjectMocks
	private UsersServiceImpl usersService;
	private User user;
	private AutoCloseable closeable;

	@BeforeEach
	public void init() {
		closeable = MockitoAnnotations.openMocks(this);
		user = new User("user1", "vvvfsv", false);
		Mockito.when(usersRepository.findByLogin("notFoundLogin")).thenThrow(EntityNotFoundException.class);
		Mockito.when(usersRepository.findByLogin(user.getLogin())).thenReturn(user);
	}

	@Test
	public void authenticateCorrect() {
		usersService.authenticate(user.getLogin(), user.getPassword());
		Mockito.verify(usersRepository).update(Mockito.any());
	}

	@Test
	public void authenticateIncorrectLogin() {
		Assertions.assertThrows(EntityNotFoundException.class,
				() -> usersService.authenticate("notFoundLogin", user.getPassword()));
	}

	@Test
	public void authenticateIncorrectPassword() {
		Assertions.assertFalse(usersService.authenticate(user.getLogin(), "notFoundPassword"));
	}

	@Test
	public void authenticateAlreadyAuthenticate() {
		User user2 = new User("user2", "rtsb", true);

		Mockito.when(usersRepository.findByLogin("user2")).thenReturn(user2);
		Assertions.assertThrows(AlreadyAuthenticatedException.class,
			() -> usersService.authenticate(user2.getLogin(), user2.getPassword()));
	}

	@AfterEach
	public void closeMocks() throws Exception {
		closeable.close();
	}
}
