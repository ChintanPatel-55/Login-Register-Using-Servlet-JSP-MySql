package com.pr.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pr.util.DButil;

public class UserDaoImp implements UserDao {
	
	
	
	@Override
	//Add User Information In DB
	public boolean addUser(User1 user) {
		String query = "insert into Registration(username, email, password, userRoll) values(?,?,?,?)";
		
		try (Connection connection = DButil.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	            preparedStatement.setString(1, user.getUsername());
	            preparedStatement.setString(2, user.getEmail());
	            preparedStatement.setString(3, user.getPassword());
	            preparedStatement.setString(4, user.getUserRoll());

	            int rowsAffected = preparedStatement.executeUpdate();
	            return rowsAffected > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	     }

	@Override
	//This Method Are check the DB Use Are Available Or Not
	public boolean isValidUser(String username, String password) {
		String query = "select * from Registration where username = ? and password = ?";
		try {
			Connection connection = DButil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			
			System.out.println("Query Are Running..");
			ResultSet resultSet = preparedStatement.executeQuery();
			return resultSet.next();
		} catch (Exception e) {
			return false;

		}
		
	}
	

	//This Methos are check the User Roll 
	public String getUserRole(String username) {
		 try {
	            Connection connection = DButil.getConnection();
	            String query1 = "SELECT userRoll FROM Registration WHERE username = ?";
	            PreparedStatement preparedStatement = connection.prepareStatement(query1);
	            preparedStatement.setString(1, username);
	            ResultSet rs = preparedStatement.executeQuery();

	            if (rs.next()) {
	                return rs.getString("userRoll");
	            }

	            rs.close();
	            preparedStatement.close();
	            connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null; // User role not found
	}

	


}
