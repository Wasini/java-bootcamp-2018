package com.globant.bootcamp.model;

import com.globant.bootcamp.services.transaction.PaymentException;

import java.util.List;

public interface ShoppingCart {

	boolean isEmpty();

	boolean contains(Item item);

	void clear();

	void addItem(Item item, int amount);

	void addItem(Item item);

	void addAll(List<Item> items);

	void removeItem(Item item, int amount);

	void removeItem(Item item);

	void purchase(Item item) throws PaymentException;

	void purchaseAll() throws PaymentException;

	int getItemCount(Item item);

	List<Item> getItems();

	List<Item> getLastPurchases();

	List<Item> getLastAdditions();

	User getCustomer();

	long getPurchaseTotal();

	long getPurchaseTotal(Item item);
}
