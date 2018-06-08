package org.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao implements IUserDao {

	@Override
	public void add(User user) throws UserException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		conn = JDBCUtils.getConnection();
		String sql = "select count(*) from user where name=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			rs = ps.executeQuery();
			
			int count = 0;
			while(rs.next()) {
				count = rs.getInt(1);
			}
			if(count > 0) {
				throw new UserException("已存在的用户");
			}
			
			sql = "insert into user(name) values(?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			int i = ps.executeUpdate();
			if(i > 0) {
				System.out.println("添加成功!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(ps);
			JDBCUtils.close(conn);
		}
	}

	@Override
	public void delete(String name) {

	}

	@Override
	public List<User> getList() {
		return null;
	}

	@Override
	public User getUser(int i) {
		return null;
	}

	public static void main(String[] args) {
		User user = new User();
		user.setName("钱六");
		UserDao userDao = new UserDao();
		try {
			userDao.add(user);
		} catch (UserException e) {
			e.printStackTrace();
		}
		
	}
}
