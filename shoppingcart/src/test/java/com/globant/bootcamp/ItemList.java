package com.globant.bootcamp;

import org.junit.experimental.theories.ParametersSuppliedBy;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@ParametersSuppliedBy(ItemListSupplier.class)
public @interface ItemList {
	int listSize();
	long minPrice();
	long maxPrice();
}
