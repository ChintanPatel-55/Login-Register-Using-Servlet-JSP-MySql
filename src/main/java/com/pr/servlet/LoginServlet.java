package com.pr.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.pr.Dao.UserDaoImp;
import com.pr.Dao.UserDao;
/**
 * Servlet implementation class LoginServlet
 */
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
//		if(userDao.isValidUser(username,password)) {
////			session.setAttribute("username",username);
//			resp.sendRedirect("welcome.jsp");
//		}else {
//			resp.sendRedirect("login.jsp");
//			System.out.println("Login Detail Are Invalid");
//		}
		
		if(username.equals("chintan") && password.equals("1234")) {
			resp.sendRedirect("UserPage.jsp");
		}else {
			resp.sendRedirect("AdminPage.jsp");
		}
	}
}
