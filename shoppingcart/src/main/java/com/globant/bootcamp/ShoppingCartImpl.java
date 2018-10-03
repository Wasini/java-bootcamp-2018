package com.globant.bootcamp;

import com.globant.bootcamp.model.Item;
import com.globant.bootcamp.model.ShoppingCart;
import com.globant.bootcamp.model.User;
import com.globant.bootcamp.services.management.CartManagementHistory;
import com.globant.bootcamp.services.transaction.PaymentService;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class ShoppingCartImpl implements ShoppingCart {

	private final User customer;

	private PaymentService paymentService;

	private CartManagementHistory cartManagementHistory;

	@Override
	public boolean isEmpty() {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	@Override
	public boolean contains(Item item) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	@Override
	public void addItem(Item item, int amount) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	@Override
	public void addItem(Item item) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	@Override
	public void addAll(List<Item> items) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	@Override
	public void removeItem(Item item, int amount) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	@Override
	public void removeItem(Item item) {
	}

	@Override
	public void purchase(Item item) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	@Override
	public void purchaseAll() {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	@Override
	public List<Item> getItems() {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	@Override
	public List<Item> getLastPurchases() {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	@Override
	public List<Item> getLastAdditions() {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	@Override
	public int getItemCount(Item item) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	@Override
	public long getPurchaseTotal() {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	@Override
	public long getPurchaseTotal(Item item) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
}
