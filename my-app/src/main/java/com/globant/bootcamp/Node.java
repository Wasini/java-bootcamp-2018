package com.globant.bootcamp;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class Node {

    @Getter @Setter @NonNull private int  value;

    @Getter @Setter private Node next = null;
}
