package com.globant.bootcamp.services.crud;

import com.globant.bootcamp.model.ShoppingCart;
import com.globant.bootcamp.persistence.CachedPersistence;
import lombok.Getter;

import java.util.UUID;

/**
 * Service for ShoppingCart CRUD operations
 */
public class ShoppingCartService implements GenericCrud<ShoppingCart, UUID> {

	@Getter
	private static final ShoppingCartService instance = new ShoppingCartService();

	private static final CachedPersistence<ShoppingCart, UUID> persistence = new CachedPersistence<>();

	private ShoppingCartService() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ShoppingCart create(ShoppingCart shoppingCart) {
		return persistence.contains(shoppingCart.getId()) ? null : persistence.persist(shoppingCart);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ShoppingCart read(UUID id) {
		return persistence.read(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ShoppingCart update(ShoppingCart shoppingCart) {
		return persistence.contains(shoppingCart.getId()) ? persistence.persist(shoppingCart) : null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(ShoppingCart shoppingCart) {
		persistence.remove(shoppingCart.getId());
	}
}