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
    public void copyConstructorHasTheSameElementsInOrder(@IntBetween(sizeMin = 0, sizeMax = 15,rangeMin = -5,rangeMax = 5,cant = 25) SinglyLinkedList l) {
        assumeTrue(!l.isEmpty());
        SinglyLinkedList copy = new SinglyLinkedList(l);
        Node copyNode = copy.getHeader().getNext();
        Node originalNode = copy.getHeader().getNext();
        int elementIndex = 0;
        while(copyNode != null){
            assertThat("Element at index " + elementIndex + "has the same value in both SLL",copyNode.getValue(),equalTo(originalNode.getValue()));
            copyNode = copyNode.getNext();
            originalNode = originalNode.getNext();
            elementIndex++;
        }
    }

    @Theory
    public void removingAnElementDecreasesSize(@IntBetween(sizeMin =1, sizeMax =10,rangeMin = -5,rangeMax = 5,cant = 25) SinglyLinkedList l) {
        final int element = 3;
        assumeTrue(l.contains(element));
        int prevSize = l.getSize();
        l.remove(element);
        assertThat("Size of list decreased after removing element "+element,prevSize,greaterThan(l.getSize()));
    }

    @Theory
    public void containsAnAddedElement(@IntBetween(sizeMin = 0, sizeMax =20,rangeMin = -1,rangeMax = 5,cant = 15) SinglyLinkedList l) {
        final int element = 5;
        l.addFirst(element);
        assertTrue("List contains element " + element + " after using add method",l.contains(element));
    }
    
    @Test
    public void noArgsConstructor_createsEmptyList() {
        SinglyLinkedList emptySll = new SinglyLinkedList();
        assertTrue(emptySll.isEmpty());
    }
    
    @Test(expected = NullPointerException.class)
    public void nullArgConstrcutor_throwException() {
        SinglyLinkedList copyList = new SinglyLinkedList(null);
    }
}

