package com.uas.util;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	public static ConnectionFactory connectionFactory = null;
	private static Properties properties = new Properties();

	private ConnectionFactory() {
		try {
			try (InputStream inputstream = new FileInputStream(".//resources//jdbc.properties");) {
				properties.load(inputstream);
				//properties.list(System.out);
				String dbDriver = properties.getProperty("db.driver");
				Class.forName(dbDriver);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static ConnectionFactory getInstance() {
		if (connectionFactory == null)
			connectionFactory = new ConnectionFactory();
		return connectionFactory;
	}

	public Connection getConnection() throws SQLException {
		Connection conn = null;
		String dbUrl = properties.getProperty("db.url");
		String dbUser = properties.getProperty("db.user");
		String dbPassword = properties.getProperty("db.password");
		conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		return conn;
	}
}