package com.globant.bootcamp.model;

import java.util.UUID;

public interface Item {
	String getName();

	long getPrice();

	UUID getId();
}
