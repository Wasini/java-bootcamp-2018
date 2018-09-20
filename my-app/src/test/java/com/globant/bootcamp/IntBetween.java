package com.globant.bootcamp;

import org.junit.experimental.theories.ParametersSuppliedBy;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Int supplier signature
 * sizeMin and sizeMax sets the ammount range of elements generated for each list
 * rangeMIn and rangeMax sets the values range for each integer in the list
 * cant sets the ammount of generated lists
 */
@Retention(RetentionPolicy.RUNTIME)
@ParametersSuppliedBy(IntBetweenSupplier.class)
public @interface IntBetween {
    int sizeMin();
    int sizeMax();
    int rangeMin();
    int rangeMax();
    int cant();
}
