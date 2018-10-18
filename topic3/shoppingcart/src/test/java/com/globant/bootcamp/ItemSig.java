package com.globant.bootcamp;

import org.junit.experimental.theories.ParametersSuppliedBy;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@ParametersSuppliedBy(ItemSupplier.class)
public @interface ItemSig {
	long minPrice();
	long maxPrice();
}
