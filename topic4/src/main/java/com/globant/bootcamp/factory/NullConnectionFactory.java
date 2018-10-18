package com.globant.bootcamp.factory;

import java.util.Properties;

import java.sql.Connection;

public class NullConnectionFactory extends ConnectionAbstractFactory {
    @Override
    public Connection getConnection(Properties properties) {
        return null;
    }
}
