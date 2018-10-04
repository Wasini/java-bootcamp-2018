package com.globant.bootcamp;

import com.globant.bootcamp.model.Item;
import com.globant.bootcamp.model.ShoppingCart;
import com.globant.bootcamp.model.Transaction;
import com.globant.bootcamp.model.User;
import com.globant.bootcamp.services.management.CartManagementHistory;
import com.globant.bootcamp.services.transaction.PaymentException;
import com.globant.bootcamp.services.transaction.PaymentService;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder(builderMethodName = "hiddenBuilder")
public class ShoppingCartImpl implements ShoppingCart {

	private final User user;

	private PaymentService paymentService;

	private CartManagementHistory cartManagementHistory;

	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private final Multiset<Item> cachedItems = HashMultiset.create();

	public static ShoppingCartImplBuilder builder(User user) {
		return hiddenBuilder().user(user);
	}

	@Override
	public boolean isEmpty() {
		return cachedItems.isEmpty();
	}

	@Override
	public boolean contains(Item item) {
		return cachedItems.contains(item);
	}

	@Override
	public void clear() {
		cachedItems.clear();
	}

	@Override
	public void addItem(@NonNull Item item, int amount) {
		cachedItems.add(item, amount);
	}

	@Override
	public void addItem(@NonNull Item item) {
		cachedItems.add(item);
	}

	@Override
	public void addAll(@NonNull List<Item> items) {
		cachedItems.addAll(items);
	}

	@Override
	public void removeItem(Item item, int amount) {
		cachedItems.remove(item, amount);
	}

	@Override
	public void removeItem(Item item) {
		cachedItems.setCount(item, 0);
	}

	@Override
	public void purchase(@NonNull Item item) throws PaymentException {
		Transaction transaction = createTransaction(getPurchaseTotal(item));
		paymentService.performTransaction(user.getAccount(), transaction);
		removeItem(item);
	}

	@Override
	public void purchaseAll() throws PaymentException {
		Transaction transaction = createTransaction(getPurchaseTotal());
		paymentService.performTransaction(user.getAccount(), transaction);
		clear();
	}

	@Override
	public List<Item> getItems() {
		return new ArrayList<>(cachedItems);
	}

	@Override
	public List<Item> getLastPurchases() {
		throw new UnsupportedOperationException("Not implemented yet");
	} //TODO

	@Override
	public List<Item> getLastAdditions() {
		throw new UnsupportedOperationException("Not implemented yet");
	} //TODO

	@Override
	public int getItemCount(@NonNull Item item) {
		return cachedItems.count(item);
	}

	@Override
	public long getPurchaseTotal() {
		return cachedItems.stream().mapToLong(Item::getPrice).sum();
	}

	@Override
	public long getPurchaseTotal(@NonNull Item item) {
		return item.getPrice() * getItemCount(item);
	}

	private Transaction createTransaction(long purchaseTotal) {
		return null; //TODO: Create real transaction
	}
}
