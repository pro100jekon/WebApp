package com.epam.smyrnov.service.impl;

import com.epam.smyrnov.entity.user.Role;
import com.epam.smyrnov.entity.user.User;
import com.epam.smyrnov.repository.UserRepository;
import com.epam.smyrnov.service.UserService;
import com.epam.smyrnov.util.HashingSha256;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {

	@Test
	public void testAddUser() {
		User user = new User();
		user.setEmail("1@mail.com");
		user.setPassword(HashingSha256.hash("password"));
		user.setRole(Role.CLIENT);
		user.setFirstName("Ivan");
		user.setLastName("Ivanov");
		UserRepository userRepository = mock(UserRepository.class);
		when(userRepository.create(user)).thenReturn(user);

		UserService userService = new UserServiceImpl(userRepository);
		User actualUser = userService.addUser("Ivan", "Ivanov", "1@mail.com", "password");
		User expectedUser = new User();
		expectedUser.setEmail("1@mail.com");
		expectedUser.setPassword(HashingSha256.hash("password"));
		expectedUser.setRole(Role.CLIENT);
		expectedUser.setFirstName("Ivan");
		expectedUser.setLastName("Ivanov");
		assertEquals(expectedUser, actualUser);
	}
}
