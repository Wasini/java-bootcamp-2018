package com.globant.bootcamp.model;

import com.globant.bootcamp.persistence.Identifiable;

/**
 * This class represents an User in the system with all his data
 *
 * @author Rodrigo Grazini
 */
public interface User extends Identifiable<String> {
	Account getAccount();
}
