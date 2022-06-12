package edu.german.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {
	private static final String IP = "192.168.2.99";
	private static final String DRV = "jdbc:postgresql://";
	private static final String DB = "language";
	private static final String PORT = ":5432/";
	private static final String USER = "german";
	private static final String PASS = "jniemiecki";

	public DbConnect() {
	}

	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(getUrl(), USER, PASS);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;

	}

	public void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private String getUrl() throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append(DRV);
		sb.append(IP);
		sb.append(PORT);
		sb.append(DB);
		return sb.toString();
	}
}
