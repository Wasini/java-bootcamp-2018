package com.globant.bootcamp;

import java.util.ResourceBundle;

import org.junit.BeforeClass;
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
    public void copyConstructorHasTheSameElementsInOrder(@IntBetween(sizeMin = 0, sizeMax = 15,rangeMin = -5,rangeMax = 5,cant = 25) SinglyLinkedList providedList) {
        assumeTrue(!providedList.isEmpty());
        SinglyLinkedList copy = new SinglyLinkedList(providedList);
        Node copyNode = copy.getHeader().getNext();
        Node originalNode = copy.getHeader().getNext();
        int elementIndex = 0;
        while(copyNode != null){
            assertThat("Element at index " + elementIndex + "has the same value in both SLL", copyNode.getValue(),equalTo(originalNode.getValue()));
            copyNode = copyNode.getNext();
            originalNode = originalNode.getNext();
            elementIndex++;
        }
    }

    @Theory
    public void removingAnElementDecreasesSize(@IntBetween(sizeMin =1, sizeMax =10,rangeMin = -5,rangeMax = 5,cant = 25) SinglyLinkedList providedList) {
        final int ELEMENT = 3;
        assumeTrue(providedList.contains(ELEMENT));
        int prevSize = providedList.getSize();
        providedList.remove(ELEMENT);
        assertThat("Size of list decreased after removing element "+ELEMENT, prevSize,greaterThan(providedList.getSize()));
    }

    @Theory
    public void containsAnAddedElement(@IntBetween(sizeMin = 0, sizeMax =20,rangeMin = -1,rangeMax = 5,cant = 15) SinglyLinkedList providedList) {
        final int ELEMENT = 5;
        providedList.addFirst(ELEMENT);
        assertTrue("List contains element " + ELEMENT + " after using add method",providedList.contains(ELEMENT));
    }
    
    @Test
    public void whenNewSinglyLinkedListIsCreatedItIsEmpty() {
        SinglyLinkedList emptySll = new SinglyLinkedList();
        assertTrue(emptySll.isEmpty());
    }
    
    @Test(expected = NullPointerException.class)
    public void whenPassingNullArgumentConstructorThenThrowsNullPointerException() {
        SinglyLinkedList copyList = new SinglyLinkedList(null);
    }
}

