package com.pr.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;



import com.pr.Dao.UserDaoImp;
import com.pr.Dao.UserDao;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	//UserData Class Object are created 
	private static UserDao userDao = new UserDaoImp();
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//Get The Input In login.jsp
		String username = req.getParameter("username");
		String password = req.getParameter("password");
	
		
		
		
		//Validation Code If user are Exists in DB or Not
//		if(userDao.isValidUser(username,password) && "admin".equalsIgnoreCase(userRoll)) {
//			resp.sendRedirect("AdminPage.jsp");	
//		}
//		else {
//			resp.sendRedirect("login.jsp");
//			System.out.println("Login Detail Are Invalid");
//		}
		
//		if(username.equals("chintan") && password.equals("1234")) {
//			resp.sendRedirect("UserPage.jsp");
//		}else {
//			resp.sendRedirect("AdminPage.jsp");
//		}
		
		if(userDao.isValidUser(username, password)) {
			
			String roll = userDao.getUserRole(username);
			
			if(roll != null) {
				if(roll.equals("Admin")) {
					resp.sendRedirect("AdminPage.jsp");
				}else if(roll.equals("User")) {
					resp.sendRedirect("UserPage.jsp");
				}else {
					resp.sendRedirect("login.jsp");
				}
			}
		}
		
		
	}
}
