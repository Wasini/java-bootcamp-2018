package com.globant.bootcamp;

import java.util.Properties;

import com.globant.bootcamp.patterns.sinlgeton.SqlConnectionSingleton;
import com.globant.bootcamp.repository.Connection;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] argsStrings) {
		Properties credentials = new Properties();
		credentials.put("user", "admin");
		credentials.put("password", "admin");
		Connection connection = SqlConnectionSingleton.getInstance("store");
		connection.connect(credentials);
	}
}
