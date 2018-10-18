package com.globant.bootcamp;

import com.globant.bootcamp.model.Item;
import com.globant.bootcamp.model.ShoppingCart;
import com.globant.bootcamp.model.Transaction;
import com.globant.bootcamp.model.User;
import com.globant.bootcamp.services.transaction.PaymentException;
import com.globant.bootcamp.services.transaction.PaymentService;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Data
@Builder(builderMethodName = "hiddenBuilder")
public class ShoppingCartImpl implements ShoppingCart {

	private final User user;

	private PaymentService paymentService;

	@Setter(AccessLevel.NONE)
	private final UUID id = UUID.randomUUID();

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
		if (!isEmpty())
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
	public void addAll(@NonNull Collection<Item> items) {
		cachedItems.addAll(items);
	}

	@Override
	public void removeItem(Item item, int amount) {
		if (contains(item))
			cachedItems.remove(item, amount);
	}

	@Override
	public void removeItem(Item item) {
		if (contains(item))
			cachedItems.setCount(item, 0);
	}

	@Override
	public void purchase(@NonNull Item item) throws PaymentException {
		if (contains(item)) {
			Transaction transaction = createTransaction(getPurchaseTotal(item));
			paymentService.performTransaction(user.getAccount(), transaction);
			removeItem(item);
		}
	}

	@Override
	public void purchaseAll() throws PaymentException {
		if (!isEmpty()) {
			Transaction transaction = createTransaction(getPurchaseTotal());
			paymentService.performTransaction(user.getAccount(), transaction);
			clear();
		}
	}

	@Override
	public Collection<Item> getItems() {
		return new ArrayList<>(cachedItems);
	}

	@Override
	public int getItemCount(Item item) {
		return cachedItems.count(item);
	}

	@Override
	public long getPurchaseTotal() {
		long total = 0L;
		if (!isEmpty())
			total = cachedItems.stream().mapToLong(Item::getPrice).sum();
		return total;
	}

	@Override
	public long getPurchaseTotal(@NonNull Item item) {
		long total = 0L;
		if (contains(item))
			total = item.getPrice() * getItemCount(item);
		return total;
	}

	private Transaction createTransaction(long purchaseTotal) {
		return null; //TODO: Create real transaction
	}
}
