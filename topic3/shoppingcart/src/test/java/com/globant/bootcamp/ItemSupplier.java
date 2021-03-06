package com.globant.bootcamp;

import com.globant.bootcamp.model.Item;
import org.junit.experimental.theories.ParameterSignature;
import org.junit.experimental.theories.ParameterSupplier;
import org.junit.experimental.theories.PotentialAssignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItemSupplier extends ParameterSupplier {
	public final static int POTENTIAL_ASSIGNEMENT_COUNT = 15;

	public final static String[] ITEM_NAMES = { "Mochila", "Mouse", "Gotita", "Fernet", "Coca", "Lapiz B2", "",
			"Deo Rexona", "Diccionario", "ZODIDUCE", "KARMAN", "ANIBIMA", "SUPH" };

	private long minPrice;

	private long maxPrice;

	@Override
	public List<PotentialAssignment> getValueSources(ParameterSignature sig) {
		ItemSig Annotation = sig.getAnnotation(ItemSig.class);
		minPrice = Annotation.minPrice();
		maxPrice = Annotation.maxPrice();

		Item mockedItem;
		List<PotentialAssignment> values = new ArrayList<>();
		for (int i = 0; i < POTENTIAL_ASSIGNEMENT_COUNT; i++) {
			mockedItem = generateMockItem();
			values.add(PotentialAssignment.forValue(mockedItem.toString(), mockedItem));
		}
		return values;
	}

	private Item generateMockItem() {
		Item mockItem = mock(Item.class);
		when(mockItem.getName()).thenReturn(getRandomName());
		when(mockItem.getPrice()).thenReturn(getRandomLong());
		return mockItem;
	}

	private String getRandomName() {
		int rnd = new Random().nextInt(ITEM_NAMES.length);
		return ITEM_NAMES[rnd];
	}

	private long getRandomLong() {
		return minPrice + (long) (Math.random() * (maxPrice - minPrice));
	}
}
