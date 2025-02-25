package com.pr.Dao;

public interface UserDao {

	//Validation Interface
    boolean isValidUser(String username, String password);

	//AddUser Interface
	boolean addUser(User1 user);
	
	String getUserRole(String username);
}
