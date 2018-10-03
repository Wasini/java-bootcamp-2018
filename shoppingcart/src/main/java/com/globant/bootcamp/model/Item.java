package com.globant.bootcamp.model;

import com.sun.corba.se.spi.ior.Identifiable;

public interface Item extends Identifiable {
	String getName();

	long getPrice();
}
