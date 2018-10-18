package com.globant.bootcamp;

import com.globant.bootcamp.model.Item;
import org.junit.experimental.theories.ParameterSignature;
import org.junit.experimental.theories.ParameterSupplier;
import org.junit.experimental.theories.PotentialAssignment;

import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItemListSupplier extends ParameterSupplier {

	public final static int POTENTIAL_ASSIGNEMENT_COUNT = 30;

	public final static String[] ITEM_NAMES = { "Mochila", "Mouse", "Gotita", "Fernet", "Coca", "Lapiz B2", "",
			"Deo Rexona", "Diccionario", "ZODIDUCE", "KARMAN", "ANIBIMA", "SUPH" };

	private List<Item> mockedItems;

	private long minPrice;

	private long maxPrice;

	@Override
	public List<PotentialAssignment> getValueSources(ParameterSignature sig) {
		ItemList Annotation = sig.getAnnotation(ItemList.class);
		final int listSize = Annotation.listSize();
		minPrice = Annotation.minPrice();
		maxPrice = Annotation.maxPrice();

		generateMockItems();
		List<PotentialAssignment> values = new ArrayList<>();
		for (int i = 0; i < POTENTIAL_ASSIGNEMENT_COUNT; i++) {
			List<Item> items = generatePotentialItemList(listSize);
			values.add(PotentialAssignment.forValue(items.toString(), items));
		}
		return values;
	}

	private void generateMockItems() {
		mockedItems = new LinkedList<>();
		for (String name : ITEM_NAMES) {
			Item mockItem = mock(Item.class);
			when(mockItem.getId()).thenReturn(UUID.randomUUID());
			when(mockItem.getName()).thenReturn(name);
			when(mockItem.getPrice()).thenReturn(getBoundedPrice());
			mockedItems.add(mockItem);
		}
	}

	private List<Item> generatePotentialItemList(int listSize) {
		List<Item> items = new ArrayList<>(listSize);
		for (int j = 0; j < listSize; j++) {
			items.add(getRandomItem());
		}
		return items;
	}

	private Item getRandomItem() {
		int chosenIndex = new Random().nextInt(mockedItems.size());
		return mockedItems.get(chosenIndex);
	}

	private long getBoundedPrice() {
		return minPrice + (long) (Math.random() * (maxPrice - minPrice));
	}
}
