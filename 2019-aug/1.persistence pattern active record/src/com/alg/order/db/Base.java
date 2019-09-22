package com.alg.order.db;

import java.sql.*;
import java.util.Properties;

public class Base {
	protected Connection connection;

	public Base() {

	}

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

	public Statement getStatement() throws SQLException {
		if (connection == null)
			connect();
		return connection.createStatement();
	}

	public String test() throws SQLException {

		Statement stmt = getStatement();
		ResultSet rset = stmt.executeQuery("select SYSDATE from dual");

		String result = "";
		// Print the result
		while (rset.next())
			result = rset.getString(1);

		// Close the RseultSet
		rset.close();

		// Close the Statement
		stmt.close();

		close();
		return result;
	}

	private void close() throws SQLException {
		connection.close();
	}

}
