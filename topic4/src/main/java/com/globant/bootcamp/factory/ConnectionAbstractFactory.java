package com.globant.bootcamp.factory;

import java.sql.Connection;
import java.util.Properties;


public abstract class ConnectionAbstractFactory {
	public abstract Connection getConnection(Properties properties);
}