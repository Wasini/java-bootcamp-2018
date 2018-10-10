package com.globant.bootcamp.model;

import com.globant.bootcamp.persistence.Identifiable;
import com.globant.bootcamp.services.transaction.PaymentException;

import java.util.Collection;
import java.util.UUID;

/**
 * This class represents a shopping cart belonging to a {@link User},
 * the cart can hold different {@link Item} which can be repeated,
 * the purpose of the cart is to get information about the items stocked
 * on it and finally to {@code purchase} some or all of them
 *
 * <p>Methods of this class can throw a <tt>PaymentException</tt> when
 * a purchase can't be completed
 *
 * @author Rodrigo Grazini
 */
public interface ShoppingCart extends Identifiable<UUID> {

	/**
	 *
	 * @return true iff the cart has no items
	 */
	boolean isEmpty();

	/**
	 * Checks if the given item is in the cart
	 * @param item to look up in the cart
	 * @return true iff item is contained in the cart
	 */
	boolean contains(Item item);

	/**
	 * If there are items in the cart it removes all the items stocked in the cart
	 */
	void clear();

	/**
	 * Adds to the cart the given item the specified amount of times
	 * Item can't be null
	 * @param item item to add in the cart
	 * @param amount times to add the item
	 */
	void addItem(Item item, int amount);

	/**
	 * Adds to the cart the given item one time
	 *
	 * <p>Item can't be null
	 * @param item item to add in the cart
	 */
	void addItem(Item item);

	/**
	 * Adds to the cart all the items holden by the collection
	 *
	 * <p>Collection can't be null
	 * @param items Collections of items to add in the cart
	 */
	void addAll(Collection<Item> items);

	/**
	 * Removes from the cart the given item the specified amount of times if present
	 * @param item item to remove
	 * @param amount times to remove the item
	 */
	void removeItem(Item item, int amount);

	/**
	 * Completely remove an item from the cart if present
	 * @param item item to remove
	 */
	void removeItem(Item item);

	/**
	 * Tries to complete the purchase of the given item if present, if item
	 * is repeated in the cart it purchases all of the times it has been added
	 *
	 * <p>After the purchase has succeed the item is removed from the cart
	 * @param item Item that will be purchased
	 * @throws PaymentException If the purchase failed when the payment was being done,
	 * the exception has a code which gives more detail about the error
	 */
	void purchase(Item item) throws PaymentException;

	/**
	 * Tries to complete the purchase of all the items in the cart if any
	 *
	 * <p>After the purchase has succeed, the cart is cleared and left empty
	 * @throws PaymentException If the purchase failed when the payment was being done,
	 * the exception has a code which gives more detail about the error
	 */
	void purchaseAll() throws PaymentException;

	/**
	 * Returns the amount of times the given item is in the cart
	 * @param item Item to check and count
	 * @return the times the item is present in the cart
	 */
	int getItemCount(Item item);

	/**
	 * Get all the items holden in the cart as a collection,
	 * the items are repeated in the collection for the count
	 * of each one
	 * @return A collections with all the items contained in the cart
	 */
	Collection<Item> getItems();

	/**
	 * @return The User owner of the cart
	 */
	User getUser();

	/**
	 * Calculates and returns the total value of all the items in the cart
	 * @return The total value of the items as a long
	 */
	long getPurchaseTotal();

	/**
	 * Calculates and returns the total value of all the instances
	 * of the given item in the cart
	 *
	 * <p>Item can't be null
	 * @param item Item to get the total cost
	 * @return The total cost of purchasing all of the given item
	 */
	long getPurchaseTotal(Item item);
}
