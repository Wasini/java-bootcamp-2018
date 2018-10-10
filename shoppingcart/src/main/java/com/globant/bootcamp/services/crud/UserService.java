package com.globant.bootcamp.services.crud;

import com.globant.bootcamp.model.User;
import com.globant.bootcamp.persistence.CachedPersistence;
import lombok.Getter;

/**
 * Service for User CRUD operations
 */
public class UserService implements GenericCrud<User, String> {
	@Getter
	private static final UserService instance = new UserService();

	private static final CachedPersistence<User, String> persistence = new CachedPersistence<>();

	private UserService() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User create(User user) {
		return persistence.contains(user.getId()) ? null : persistence.persist(user);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User read(String id) {
		return persistence.read(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User update(User user) {
		return persistence.contains(user.getId()) ? persistence.persist(user) : null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(User user) {
		persistence.remove(user.getId());
	}
}
