package com.globant.bootcamp.patterns.factory;

import com.globant.bootcamp.repository.PostgresConnection;
import com.globant.bootcamp.repository.Connection;

public class PostgresConnectionFactory extends ConnectionAbstractFactory {
    @Override
    public Connection getConnection(String dbName) {
        return new PostgresConnection(dbName);
    }
}
