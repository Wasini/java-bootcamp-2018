package com.globant.bootcamp.services.crud;

import com.globant.bootcamp.model.Item;
import com.globant.bootcamp.persistence.CachedPersistence;
import lombok.Getter;

import java.util.UUID;

/**
 * Service for Item CRUD operations
 */
public class ItemService implements GenericCrud<Item, UUID> {

	@Getter
	private static final ItemService instance = new ItemService();

	private static final CachedPersistence<Item, UUID> persistence = new CachedPersistence<>();

	private ItemService() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Item create(Item item) {
		return persistence.contains(item.getId()) ? null : persistence.persist(item);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Item read(UUID id) {
		return persistence.read(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Item update(Item item) {
		return persistence.contains(item.getId()) ? persistence.persist(item) : null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(Item item) {
		persistence.remove(item.getId());
	}
}
