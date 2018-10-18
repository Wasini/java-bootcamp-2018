package com.globant.bootcamp.factory;

import com.globant.bootcamp.DBType;;

public class FactoryProducer {

	public static ConnectionAbstractFactory getFactory(DBType choice) {
		ConnectionAbstractFactory selectedFactory;
		switch (choice) {
		case MYSQL:
			selectedFactory = new SqlConnectionFactory();
			break;
		default:
			selectedFactory = new NullConnectionFactory();
			break;
		}
		return selectedFactory;
	}
}
