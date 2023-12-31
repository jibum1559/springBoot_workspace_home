package com.kh.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.common.JDBCTemplate;
import com.kh.model.vo.DTO;

public class DAO {
	public static List<DTO> selectAllUsers() throws SQLException {
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<DTO> userList = new ArrayList<>();
		
		String sql = "SELECT * FROM TEST_USER";
		
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		
		while(rs.next()) {
			DTO user = new DTO();
			user.setUser_number(rs.getInt("user_number"));
			user.setUser_id(rs.getString("user_id"));
			user.setUser_name(rs.getString("user_name"));
			user.setUser_age(rs.getInt("user_age"));
			userList.add(user);
		}
		return userList;
	}
	
	public static List<DTO> selectUserById(String userId) throws SQLException {
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<DTO> userList = new ArrayList<>();
		
		String sql = "SELECT * FROM TEST_USER WHERE USER_ID = ?";
		
		ps = conn.prepareStatement(sql);
		ps.setString(1, userId);
		
		rs = ps.executeQuery();
		
		while(rs.next()) {
			DTO user = new DTO();
			user.setUser_number(rs.getInt("user_number"));
			user.setUser_id(rs.getString("user_id"));
			user.setUser_name(rs.getString("user_name"));
			user.setUser_age(rs.getInt("user_age"));
			userList.add(user);
			
		}
		/*} finally {
		//close resultSet - PreparedStatement - Connection */
		return userList;
	}
	
}
