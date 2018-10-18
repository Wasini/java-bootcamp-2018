package com.globant.bootcamp.services.crud;

import com.globant.bootcamp.persistence.Identifiable;

/**
 * Generic interface for CRUD operations of objects that are
 * identifiable with a primary key
 * @param <T> Object type to store
 * @param <PK> The type of the primary key of the objects
 */
public interface GenericCrud<T extends Identifiable<PK>, PK> {
	/**
	 * Stores the specified object if absent
	 * @param t Object to store in persistence
	 * @return The stored object if succeeded else returns null
	 */
	T create(T t);

	/**
	 * Retrieves the object with the given id if present
	 * @param id The object id to retrieve
	 * @return The object with the given id if present, else returns null
	 */
	T read(PK id);

	/**
	 * Updates the given object in storage if present
	 * @param t The object to update
	 * @return The updated object or nulls if absent
	 */
	T update(T t);

	/**
	 * Deletes the given object in storage
	 * @param t The object to delete
	 */
	void delete(T t);
}
