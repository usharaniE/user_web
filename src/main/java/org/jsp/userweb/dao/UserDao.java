package org.jsp.userweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jsp.userweb.dto.User;

public class UserDao {
	static Connection connection;
	PreparedStatement preparedStatement;
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/user_web";
	public static final String USER = "root";
	public static final String PASSWORD = "root";

	public void closeResources(Connection con, PreparedStatement pst, ResultSet rs) {
		try {
			if (con != null)
				con.close();
			if (pst != null)
				pst.close();
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	{
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void saveUser(User user) {
		try {
			preparedStatement = connection.prepareStatement("insert into user values(?,?,?,?)");
			preparedStatement.setInt(1, user.getId());
			preparedStatement.setString(2, user.getName());
			preparedStatement.setLong(3, user.getPhone());
			preparedStatement.setString(4, user.getGender());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(connection, preparedStatement, null);
		}
	}

	public void updateUser(User user) {
		try {
			preparedStatement = connection.prepareStatement("update user set name=?, phone=?,gender=? where id=?");
			preparedStatement.setInt(4, user.getId());
			preparedStatement.setString(1, user.getName());
			preparedStatement.setLong(2, user.getPhone());
			preparedStatement.setString(3, user.getGender());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(connection, preparedStatement, null);
		}
	}

	public User getUser(int id) {
		String query = "select * from user where id=?";
		User user = new User();
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user.setId(resultSet.getInt(1));
				user.setName(resultSet.getString(2));
				user.setGender(resultSet.getString(4));
				user.setPhone(resultSet.getLong(3));
			} else
				user = null;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(connection, preparedStatement, resultSet);
		}
		return user;
	}

	public List<User> getAllUsers() {
		String query = "select * from user";
		List<User> users = new ArrayList<User>();
		ResultSet resultSet = null;
		try {

			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt(1));
				user.setName(resultSet.getString(2));
				user.setGender(resultSet.getString(4));
				user.setPhone(resultSet.getLong(3));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(connection, preparedStatement, resultSet);
		}
		return users;
	}

	public void deleteUser(int id) {
		String query = "delete from user where id=?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(connection, preparedStatement, null);
		}
	}
}