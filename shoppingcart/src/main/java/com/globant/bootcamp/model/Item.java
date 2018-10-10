package com.globant.bootcamp.model;

import java.util.UUID;

/**
 * This class represents a purchasable object with an unique ID
 * and all its relevant data
 *
 * @author Rodrigo Grazini
 */
public interface Item {
	/**
	 * @return The name of the item
	 */
	String getName();

	/**
	 * @return The price in Pesos of the item as a long value
	 */
	long getPrice();

	/**
	 * @return The unique identifier of the Item
	 */
	UUID getId();
}
