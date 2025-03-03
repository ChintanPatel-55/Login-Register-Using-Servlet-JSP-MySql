package com.pr.Dao;

public interface UserDao {

	//Validation Interface
    boolean isValidUser(String email, String password);

	//AddUser Interface
//	boolean addUser(User1 user);
	
	//GetUser Roll
	String getUserRole(String email);
	
	String addAddress(String address, int userID);
	
}
