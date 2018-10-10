package com.globant.bootcamp;

import com.globant.bootcamp.model.Item;
import com.globant.bootcamp.services.crud.ItemService;
import org.junit.After;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(Theories.class)
public class ItemServiceTest {

	ItemService itemService = ItemService.getInstance();

	List<Item> addedItems = new ArrayList<>();

	@After
	public void tearDown() {
		for (Item item : addedItems) {
			itemService.delete(item);
		}
		addedItems.clear();
	}

	@Theory
	public void readingAnItemIdShoulReturnTheRequestedItem(
			@ItemSig(minPrice = 0L, maxPrice = 99999999999L) Item item) {
		storeItem(item);
		assertThat(itemService.read(item.getId()), equalTo(item));
	}

	@Theory
	public void updatingAnItemShouldChangeTheUpdatedAtributesInStorage(
			@ItemSig(minPrice = 0L, maxPrice = 99999999999L) Item item) {
		storeItem(item);
		String newName = "New name";
		when(item.getName()).thenReturn(newName);
		itemService.update(item);
		assertThat(itemService.read(item.getId()).getName(), equalTo(newName));
	}

	private void storeItem(Item item) {
		itemService.create(item);
		addedItems.add(item);
	}
}
