package com.pr.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


import com.pr.Dao.User1;
import com.pr.Dao.UserDao;
import com.pr.Dao.UserDaoImp;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static UserDao userDao = new UserDaoImp();
		
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//Get The Input In register.jsp
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String option = req.getParameter("option");
		 
		  //Create The User1 Object And set the Value Of DB
		  User1 user = new User1();
		  user.setUsername(username);
		  user.setEmail(email);
		  user.setPassword(password);
		  user.setUserRoll(option);
		  			
		
		//This Condition are Check The User Are Added Or Not
		if(userDao.addUser(user)) {
			resp.sendRedirect("login.jsp");
		}else {
			resp.sendRedirect("register.jsp");
		}
	}

}
