package com.kh.common;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCTemplate {
	public static Connection getConnection() throws SQLException {
		return OracleConnectionProvider.getConnection();
	}
}
