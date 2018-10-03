package com.globant.bootcamp;

import com.globant.bootcamp.model.Item;
import com.globant.bootcamp.model.ShoppingCart;
import com.globant.bootcamp.model.User;
import com.globant.bootcamp.services.management.CartManagementHistory;
import com.globant.bootcamp.services.transaction.PaymentException;
import com.globant.bootcamp.services.transaction.PaymentService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;
import java.util.Random;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(Theories.class)
public class ShoppingCartImplTest {

	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	private final static int AMOUNT_TO_ADD = 10;

	@Mock
	private User customer;

	@Mock
	private PaymentService paymentService;

	@Mock
	private CartManagementHistory cartManagementHistory;

	@InjectMocks
	private ShoppingCartImpl cart;

	@Before
	public void setUp() {
		cart = new ShoppingCartImpl(customer);
	}

	@Test
	public void shouldBeEmptyWhenNewCartIsCreated() {
		ShoppingCart newCart = new ShoppingCartImpl(customer);
		assertThat(newCart.isEmpty(), is(true));
	}

	@Test
	public void purchaseTotalShouldBeZeroWhenCartIsEmpty() {
		ShoppingCart newCart = new ShoppingCartImpl(customer);
		assertThat(newCart.getPurchaseTotal(), equalTo(0L));
	}

	@Theory
	public void shouldNotBeEmptyAfterAddingAnItem(
			@ItemSig(minPrice = 0L, maxPrice = 572L) Item item) {
		assumeTrue(cart.isEmpty());
		cart.addItem(item);
		assertFalse(cart.isEmpty());
	}

	@Test(expected = NullPointerException.class)
	public void shouldThrowExceptionWhenAddingNullItem() {
		cart.addItem(null);
	}

	@Theory
	public void shouldBeEmptyAfterCallingClear(
			@ItemList(listSize = 10, minPrice = 0L, maxPrice = 572L) List<Item> itemsToAdd) {
		cart.addAll(itemsToAdd);
		assumeTrue(!cart.isEmpty());
		cart.clear();
		assertTrue(cart.isEmpty());
	}

	@Theory
	public void cartShouldHaveItemWhenAdded(
			@ItemSig(minPrice = 0L, maxPrice = 5023L) Item item) {
		cart.addItem(item);
		assertThat(cart.getItems(), contains(item));
	}

	@Theory
	public void purchaseAmountShouldIncreaseByItemPricePerAmountWhenItemIsAdded(
			@ItemList(listSize = 10, minPrice = 0L, maxPrice = 5700L) List<Item> itemsToAdd) {
		long itemPrice;
		long currentAmount;

		for (Item item : itemsToAdd) {
			itemPrice = item.getPrice();
			currentAmount = cart.getPurchaseTotal();
			cart.addItem(item);
			assertThat(cart.getPurchaseTotal(), comparesEqualTo(currentAmount + itemPrice));
		}
	}

	@Theory
	public void itemCountShouldIncreaseWhenItemIsAdded(
			@ItemList(listSize = 10, minPrice = 0L, maxPrice = 5700L) List<Item> itemsToAdd) {
		int addTimes;
		int currentCount;

		for (Item item : itemsToAdd) {
			addTimes = getRandomAmount(AMOUNT_TO_ADD);
			currentCount = cart.getItemCount(item);
			cart.addItem(item, addTimes);
			assertThat(cart.getItemCount(item), is(equalTo(currentCount + addTimes)));
		}
	}

	@Theory
	public void shouldNotContainItemAfterRemovingIt(
			@ItemList(listSize = 10, minPrice = 0L, maxPrice = 5700L) List<Item> itemsToAdd) {
		cart.addAll(itemsToAdd);
		Item itemToRemove = itemsToAdd.get(new Random().nextInt(itemsToAdd.size()));
		assumeTrue(cart.contains(itemToRemove));

		cart.removeItem(itemToRemove);
		assertThat("List from getItems() contains the removed item", cart.getItems(),
				not(contains(itemToRemove)));
		assertThat("Cart says it contains the removed item", cart.contains(itemToRemove), is(false));
	}

	@Theory
	public void purchaseAmountShouldDecreaseByItemPricePerAmountWhenItemIsRemoved(
			@ItemSig(minPrice = 0L, maxPrice = 50203L) Item item) {
		final int ADDED_TIMES = getRandomAmount(AMOUNT_TO_ADD);
		final long ITEM_PRICE = item.getPrice();
		final int REMOVE_TIMES = getRandomAmount(ADDED_TIMES);

		cart.addItem(item, ADDED_TIMES);
		final long TOTAL_BEFORE_REMOVE = cart.getPurchaseTotal();

		cart.removeItem(item, REMOVE_TIMES);
		assertThat(cart.getPurchaseTotal(), is(equalTo(TOTAL_BEFORE_REMOVE - (ITEM_PRICE * REMOVE_TIMES))));
	}

	@Theory
	public void ifItemIsInCartThenItemCountShouldDecreaseAfterRemovingItOneTime(
			@ItemSig(minPrice = 0L, maxPrice = 1000L) Item item) {
		final long ITEM_PRICE = item.getPrice();
		final int ADD_TIMES = 1 + getRandomAmount(AMOUNT_TO_ADD);

		cart.addItem(item, ADD_TIMES);
		final long TOTAL_BEFORE_REMOVE = cart.getPurchaseTotal();

		cart.removeItem(item, 1);
		assertThat(cart.getPurchaseTotal(), comparesEqualTo(TOTAL_BEFORE_REMOVE - ITEM_PRICE));
	}

	@Theory
	public void whenItemIsPurchasedThenTheItemShouldNotBeInTheCart(
			@ItemSig(minPrice = 0L, maxPrice = 2500L) Item item) throws PaymentException {
		when(paymentService.validatePayment(anyLong())).thenReturn(true);
		cart.addItem(item, getRandomAmount(AMOUNT_TO_ADD));
		cart.purchase(item);
		assertFalse(cart.contains(item));
	}

	@Test(expected = NullPointerException.class)
	public void purchaseOfNullItemShouldThrowException() throws PaymentException {
		when(paymentService.validatePayment(anyLong())).thenReturn(true);
		cart.purchase(null);
	}

	@Theory
	public void whenPurchasingAllThenCartShouldBeEmpty(
			@ItemList(listSize = 15, minPrice = 0L, maxPrice = 5700L) List<Item> itemsToAdd) throws PaymentException {
		cart.addAll(itemsToAdd);
		assumeTrue(!cart.isEmpty());
		when(paymentService.validatePayment(anyLong())).thenReturn(true);

		cart.purchaseAll();
		assertTrue(cart.isEmpty());
	}

	@Theory
	public void getItemsShouldReturnAListWithAllTheItemsAddedToCart(
			@ItemList(listSize = 10, minPrice = 0L, maxPrice = 100500L) List<Item> itemsToAdd) {
		cart.addAll(itemsToAdd);

		assertThat(cart.getItems(), containsInAnyOrder(itemsToAdd));
	}

	@Theory
	public void getItemCountShouldReturnTheCurrentCountOfItemsInTheCart(
			@ItemSig(minPrice = 0L, maxPrice = 100L) Item item) {
		assumeTrue(!cart.contains(item));
		final int ADDED_AMOUNT = getRandomAmount(AMOUNT_TO_ADD * 100);

		cart.addItem(item, ADDED_AMOUNT);
		assertThat(cart.getItemCount(item), equalTo(ADDED_AMOUNT));
		cart.addItem(item, ADDED_AMOUNT);
		assertThat("Failed when adding the same item twice", cart.getItemCount(item), equalTo(ADDED_AMOUNT * 2));
	}

	@Theory
	public void getPurchaseTotalShouldReturnTheTotalCostOfAllTheItemsInTheCart(
			@ItemList(listSize = 15, minPrice = 0L, maxPrice = 6000L) List<Item> itemsToAdd) {
		long total = cart.getPurchaseTotal();
		for (Item item : itemsToAdd) {
			total += item.getPrice();
		}

		cart.addAll(itemsToAdd);
		assertThat(cart.getPurchaseTotal(), is(equalTo(total)));
	}

	private static int getRandomAmount(int maxInclusive) {
		return new Random().nextInt(maxInclusive + 1);
	}
}