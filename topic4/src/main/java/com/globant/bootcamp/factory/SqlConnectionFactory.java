package com.globant.bootcamp.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqlConnectionFactory extends ConnectionAbstractFactory {
    
    private final static Logger LOGGER = LoggerFactory.getLogger(SqlConnectionFactory.class);
    
    @Override
    public Connection getConnection(Properties properties) {
        Connection connection = null;
        String user = properties.getProperty("user");
        String url = properties.getProperty("url");
        try {
            LOGGER.debug("Creating connection for {}", url);
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql:"+ url+ "?"+"user="+user);
        } catch(Exception e) {
            LOGGER.error("Failed creating connection for url {} with user {}",url,user);
            LOGGER.error("Exception: ", e);
            
        }
        return connection;
    }
}
