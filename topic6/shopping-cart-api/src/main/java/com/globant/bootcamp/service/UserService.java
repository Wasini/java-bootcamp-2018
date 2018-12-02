package com.globant.bootcamp.service;

import com.globant.bootcamp.domain.User;

import java.util.List;

public interface UserService {
	User createOrUpdate(User user);

	boolean userAlreadyExists(User user);

	boolean userAlreadyExists(Long id);

	void remove(Long id);

	User get(Long id);

	List<User> getAll();
}
