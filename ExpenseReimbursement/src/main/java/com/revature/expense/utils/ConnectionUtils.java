package com.revature.expense.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
	private Connection con;
	private static ConnectionUtils connectionUtils;

	public Connection getConnection() {
		return con;
	}

	private ConnectionUtils() throws SQLException {
		String url = System.getenv("DB_URL");
		String dbName = System.getenv("DB_NAMEP1");
		String user = System.getenv("DB_USER");
		String pwd = System.getenv("DB_PASS");
		try {
			Class.forName("org.postgresql.Driver");

			this.con = DriverManager.getConnection(url + dbName, user, pwd);

		} catch (ClassNotFoundException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ConnectionUtils getInstance() throws SQLException {
		if (connectionUtils == null) {
			connectionUtils = new ConnectionUtils();
		} else if (connectionUtils.getConnection().isClosed()) {
			connectionUtils = new ConnectionUtils();
		}
		return connectionUtils;

	}
}
