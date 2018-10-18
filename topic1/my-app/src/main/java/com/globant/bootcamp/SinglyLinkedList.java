package com.globant.bootcamp;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.NonFinal;

/**
 * Representation of a singled linked list of integers with a fictitious node with value 0
 */
@Value
public class SinglyLinkedList {

    @NonNull Node header;
    
    @NonFinal @NonNull int size;

    public SinglyLinkedList() {
        /* Fictitious Node */
        header = new Node(0);
        size = 0;
    }

    public SinglyLinkedList(@NonNull SinglyLinkedList another) {
        header = another.getHeader();
        size = another.getSize();
    }
    
    public Node getHeader(){
        return new Node(header);
    }

    public boolean contains(int value) {
        Node current = header.getNext();
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
        newNode.setNext(header.getNext());
        header.setNext(newNode);
        size++;
    }

    public void removeValue(int value) {
        Node currentNode = header.getNext();
        Node previousNode = header;

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
        return size == 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        Node currentNode = header.getNext();
        for (int i = 0; i < size; i++) {
            stringBuilder.append(currentNode.getValue());
            if (i < getSize() - 1)
                stringBuilder.append(",");
            currentNode = currentNode.getNext();
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
