package com.produtos.jdbc;

import java.util.Optional;

public class ConnectionConfig {

	public static String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static String JDBC = "jdbc:mysql";
	public static String HOST = "localhost";
	public static String PORT = "3306";
	public static String DATABASE = "produtos";
	public static String USER = "root";
	public static String PASSWORD = "";

	public static String getUrl() {
		StringBuilder url = new StringBuilder();

		url.append(JDBC);
		url.append("://");
		url.append(HOST);

		if (!Optional.ofNullable(PORT).orElse("").trim().isEmpty()) {
			url.append(":");
			url.append(PORT);
		}

		url.append("/");
		url.append(DATABASE);

		return url.toString();
	}

}
