package com.alg.order.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class AbstractDao<E extends Entity> implements IDao<E> {
	protected Connection connection;

	protected void connect() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/test";
		String user = "algo";
		String password = "algo";
		// Load the Oracle JDBC driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Properties properties = new Properties();
		properties.setProperty("user", user);
		properties.setProperty("password", password);
		properties.setProperty("useSSL", "false");
		properties.setProperty("autoReconnect", "true");

		// Connect to the database
		connection = DriverManager.getConnection(url, properties);
	}
	
	protected void close() throws SQLException {
		connection.close();
	}


}
