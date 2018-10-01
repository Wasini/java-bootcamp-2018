package com.globant.bootcamp;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * Representation of a singled linked list of integers with a fictitious node with value 0
 */
@Getter
@Setter
public class SinglyLinkedList {

    @NonNull private Node header;
    @NonNull private int  size;

    public SinglyLinkedList() {
        /* Fictitious Node */
        this.setHeader(new Node(0));
        this.setSize(0);
    }

    public SinglyLinkedList(@NonNull SinglyLinkedList another) {
        /* Ficticious Node */
        this.setHeader(new Node(0));
        Node previousNode = this.getHeader();
        Node currentNode = another.getHeader().getNext();

        for (int i = 0; i < another.getSize(); i++) {
            Node newNode = new Node(currentNode.getValue());
            previousNode.setNext(newNode);
            previousNode = newNode;
            currentNode = currentNode.getNext();
        }
        setSize(another.getSize());
    }

    public boolean contains(int value) {
        Node current = getHeader().getNext();
        boolean founded = false;
        while (current != null && !founded) {
            if (current.getValue() == value)
                founded = true;
            current = current.getNext();
        }
        return founded;
    }

    public void addFirst(int value) {
        Node newNode = new Node(value);
        newNode.setNext(getHeader().getNext());
        getHeader().setNext(newNode);
        size++;
    }

    public void remove(int value) {
        Node currentNode = getHeader().getNext();
        Node previousNode = getHeader();

        while (currentNode != null && currentNode.getValue() != value) {
            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }

        if (currentNode != null) {
            previousNode.setNext(currentNode.getNext());
            size--;
        }
    }

    public boolean isEmpty() {
        return getHeader().getNext() == null;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        Node currentNode = getHeader().getNext();
        for (int i = 0; i < getSize(); i++) {
            stringBuilder.append(currentNode.getValue());
            if (i < getSize() - 1)
                stringBuilder.append(",");
            currentNode = currentNode.getNext();
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
