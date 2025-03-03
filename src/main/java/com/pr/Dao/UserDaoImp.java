package com.pr.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pr.util.DButil;

public class UserDaoImp implements UserDao {
	
	
	
//	@Override
	//Add User Information In DB
//	public boolean addUser(User1 user) {
//		String query = "insert into Registration(fname, lname, email, password, phone,userRoll) values(?,?,?,?,?,?)";
////		String addressInsertQuery = "insert into addresses (id, address) VALUES (?, ?)";
//		
//		try (Connection connection = DButil.getConnection();
//	             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
//
////	            preparedStatement.setString(1, user.getUsername());
//				preparedStatement.setString(1, user.getFname());
//				preparedStatement.setString(2, user.getLname());
//	            preparedStatement.setString(3, user.getEmail());
//	            preparedStatement.setString(4, user.getPassword());
//	            preparedStatement.setString(5, user.getPhone());
//	            preparedStatement.setString(6, user.getUserRoll());
//
//	            int rowsAffected = preparedStatement.executeUpdate(); 	
//	            
//	            return rowsAffected > 0;
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	            return false;
//	        }
//	     }

	@Override
	//This Method Are check the DB Use Are Available Or Not
	public boolean isValidUser(String email, String password) {
		String query = "select * from Registration where email = ? and password = ?";
		try {
			Connection connection = DButil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			
			System.out.println("Query Are Running..");
			ResultSet resultSet = preparedStatement.executeQuery();
		
			return resultSet.next();
		} catch (Exception e) {
			return false;

		}
		
	}
	

	//This Methos are check the User Roll 
	public String getUserRole(String email) {
		 try {
	            Connection connection = DButil.getConnection();
	            String query1 = "SELECT userRoll FROM Registration WHERE email = ?";
	            PreparedStatement preparedStatement = connection.prepareStatement(query1);
	            preparedStatement.setString(1, email);
	            ResultSet resultSet1 = preparedStatement.executeQuery();

	            if (resultSet1.next()) {
	                return resultSet1.getString("userRoll");
	            }

	            resultSet1.close();
	            preparedStatement.close();
	            connection.close();
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null; // User role not found
	}

	@Override
	public String addAddress(String address, int userID) {
		
		try {
			
			Connection connection = DButil.getConnection();
			String query2 = "insert into addresses(add_id,address) values(?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query2);
			
			preparedStatement.setInt(1, userID);
			preparedStatement.setString(2, address);
			preparedStatement.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
}
