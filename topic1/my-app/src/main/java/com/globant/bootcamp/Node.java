package com.globant.bootcamp;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class Node {
    @NonNull private int  value;
    private Node next = null;
    
    /* Recursive copy constructor */
    public Node(Node original) {
        value = original.getValue();
        next = original.getNext() == null ? null : new Node(original.getNext());
    }
}
