package com.kh.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnectionProvider {
	private static final String jdbcUrl = "jdbc:oracle:thin:@61.83.168.218:1521:xe";
	private static final String jdbcUser = "khcafe";
	private static final String jdbcPassword = "khcafe";
	
	public static Connection getConnection() throws SQLException {
		
		try {
			Class.forName("Oracle.jdbc.OracleDriver");
			return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
			
		} catch (ClassNotFoundException e) {
			throw new SQLException("jdbc 드라이버를 찾을 수 없습니다.", e);
		}
	}
}
