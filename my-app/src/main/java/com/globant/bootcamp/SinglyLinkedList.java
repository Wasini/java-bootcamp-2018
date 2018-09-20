package com.globant.bootcamp;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * Representation of a singled linked list of integers with a fictitious node with value 0
 */
public class SinglyLinkedList {

    @Setter @Getter @NonNull private Node header;
    @Setter @Getter @NonNull private int  size;

    public SinglyLinkedList() {
        /* Fictitious Node */
        this.setHeader(new Node(0));
        this.setSize(0);
    }

    /**
     * Creates a new list containing all the values from @another list in the same order
     */
    public SinglyLinkedList(@NonNull SinglyLinkedList another) {
        /* Ficticious Node */
        this.setHeader(new Node(0));
        Node prev = this.getHeader();
        Node currAnother = another.getHeader().getNext();

        for (int i = 0; i < another.getSize(); i++) {
            Node newn = new Node(currAnother.getValue());
            prev.setNext(newn);
            prev = newn;
            currAnother = currAnother.getNext();
        }
        setSize(another.getSize());
    }

    /**
     * Search for a value in the list and returns true if found
     * @param value to find
     * @return true if value is on the list, false otherwise
     */
    public boolean contains(int value) {
        Node current = getHeader().getNext();
        while (current != null) {
            if (current.getValue() == value)
                return true;
            current = current.getNext();
        }
        return false;
    }

    /**
     * Adds a value at the head of the list
     * @param value to be added
     */
    public void addFirst(int value) {
        Node n = new Node(value);
        n.setNext(getHeader().getNext());
        getHeader().setNext(n);
        setSize(getSize() + 1);
    }

    /**
     * Removes a given value from the list if present
     * @param value to be removed
     */
    public void remove(int value) {
        Node current = getHeader().getNext();
        Node previous = getHeader();

        while (current != null && current.getValue() != value) {
            previous = current;
            current = current.getNext();
        }

        if (current != null) {
            previous.setNext(current.getNext());
            setSize(getSize() - 1);
        }
    }

    /**
     * Checks whether or not the current list has not values.
     * @return true iff the current list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return getHeader().getNext() == null;
    }

    public String toString() {
        String res = "[";
        Node curr = getHeader().getNext();
        for (int i = 0; i < getSize(); i++) {
            res += curr.getValue();
            if (i < getSize() - 1)
                res += ",";
            curr = curr.getNext();
        }
        return res + "]";
    }
}
