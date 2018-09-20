package com.globant.bootcamp;

import org.junit.experimental.theories.ParameterSignature;
import org.junit.experimental.theories.ParameterSupplier;
import org.junit.experimental.theories.PotentialAssignment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * SinglyLinkedList supplier with parametrized data generation
 * IMPORTANT: This supplier uses methods from the SinglyLinkedList class it self
 * to generate instances, the supplier correctness assumes that the methods used
 * from the SinglyLinkedList are proved to be correct(Pass tests)
 * 
 * Also, this is used as demostration, probably it will be simplier to just desing tests
 * providing instances for values of interest in the domain, like empty list, list with just one element
 * and withy many elements, negative integers, zero, positive integer, MAX_INT, MIN_INT, etc.
 */
public class IntBetweenSupplier extends ParameterSupplier {
    @Override
    public List<PotentialAssignment> getValueSources(ParameterSignature sig){
        IntBetween Annotation = sig.getAnnotation(IntBetween.class);
        int sizeMin = Annotation.sizeMin();
        int sizeMax = Annotation.sizeMax();
        int rangeMin = Annotation.rangeMin();
        int rangeMax = Annotation.rangeMax();
        int cant = Annotation.cant();
        List<PotentialAssignment> values = new ArrayList<PotentialAssignment>();
        for(int k=0; k <= cant; k++) {
            int range = ThreadLocalRandom.current().nextInt(sizeMin,sizeMax+1);
            SinglyLinkedList l = new SinglyLinkedList();
            for(int i = 0; i < range; i++ ) {
                l.addFirst(ThreadLocalRandom.current().nextInt(rangeMin,rangeMax+1));
            }
            values.add(PotentialAssignment.forValue(l.toString(),l));
        }
        return values;
    }
}
