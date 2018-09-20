package com.globant.bootcamp;

import org.junit.Test;
import org.junit.experimental.theories.*;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

/**
 * Tests for class SinglyLinkedList using Theories with a parameter supplier that generates
 * SinglyLinkedLists with random values, @see com.globant.bootcamp.IntBetweenSupplier
 */
@RunWith(Theories.class)
public class SinglyLinkedListTest {

    @Theory
    public void copyConstructorHasTheSameElementsInOrder(@IntBetween(sizeMin =0, sizeMax =15,rangeMin = -5,rangeMax = 5,cant = 25) SinglyLinkedList l) {
        assumeTrue(!l.isEmpty());
        SinglyLinkedList copy = new SinglyLinkedList(l);
        Node copyNode = copy.getHeader().getNext();
        Node originalNode = copy.getHeader().getNext();
        while(copyNode != null){
            assertThat(copyNode.getValue(),equalTo(originalNode.getValue()));
            copyNode = copyNode.getNext();
            originalNode = originalNode.getNext();
        }
    }

    @Theory
    public void removingAnElementDecreasesSize(@IntBetween(sizeMin =1, sizeMax =10,rangeMin = -5,rangeMax = 5,cant = 25) SinglyLinkedList l) {
        assumeTrue(l.contains(3));
        int prevSize = l.getSize();
        l.remove(3);
        assertThat(prevSize,greaterThan(l.getSize()));
    }

    @Theory
    public void containsAnAddedElement(@IntBetween(sizeMin = 0, sizeMax =20,rangeMin = -1,rangeMax = 5,cant = 25) SinglyLinkedList l) {
        l.addFirst(5);
        assertTrue(l.contains(5));
    }
    
    @Test
    public void aNewListIsEmpty() {
        SinglyLinkedList emptySll = new SinglyLinkedList();
        assertTrue(emptySll.isEmpty());
    }
}

