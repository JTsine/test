package org.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class TestJDBC {

	public static void main(String[] args) {
		new TestJDBC().testInst();
	}
	
	public void testQuesy() {
//		String url = "jdbc:mysql://localhost:3306/test?user=minty&password=greatsqldb";
		String url = "jdbc:mysql://localhost:3306/test";
		String user = "root";
		String password = "root";
		
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			/*
			 * 1.创建connection 包java.sql
			 * 		创建conn相应的字符串与连接用户与密码
			 */
			conn = DriverManager.getConnection(url, user, password);
			
			/*
			 * 2. 创建sql
			 * 执行sql需要创建statement
			 */
			String sql = "select * from user";
			statement = conn.createStatement();
			/*
			 * 3.获取sql后
			 * 如果是更新直接使用 statement.executeUpdate(sql);
			 * 如果是执行查询使用 statement.executeQuery(sql);返回Resutlset
			 */
			resultSet = statement.executeQuery(sql);
			/*
			 * 遍历记录集统一方式
			 */
			while(resultSet.next()) {
				System.out.println(resultSet.getInt("id"));
				System.out.println(resultSet.getString("name"));
				System.out.println(resultSet.getString(2));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			/*
			 * 关闭连接
			 */
			try {
				resultSet.close();
				statement.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void testInst() {
		String url = "jdbc:mysql://localhost:3306/test";
		String user = "root";
		String password = "root";
		
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			/*
			 * 1.创建connection 包java.sql
			 * 		创建conn相应的字符串与连接用户与密码
			 */
			conn = DriverManager.getConnection(url, user, password);
			
			/*
			 * 2. 创建sql
			 * 执行sql需要创建statement
			 */
			String sql = "insert into user(name) values(?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "李四");
			/*
			 * 3.获取sql后
			 * 如果是更新直接使用 statement.executeUpdate(sql);
			 * 如果是执行查询使用 statement.executeQuery(sql);返回Resutlset
			 */
			int i = ps.executeUpdate();
			if(i > 0) {
				System.out.println("插入成功");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			/*
			 * 关闭连接
			 */
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
