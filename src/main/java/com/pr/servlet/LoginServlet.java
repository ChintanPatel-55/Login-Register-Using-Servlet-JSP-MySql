package com.pr.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pr.Dao.UserDaoImp;
import com.pr.util.DButil;
import com.pr.Dao.UserDao;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	//UserData Class Object are created 
	private static UserDao userDao = new UserDaoImp();
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//Get The Input In login.jsp
		String email = req.getParameter("email");
		String password = req.getParameter("password");
	
		if(userDao.isValidUser(email, password)) {
			
			String roll = userDao.getUserRole(email);
			
			if(roll != null) {
				
				if(roll.equals("Admin")) {
					HttpSession session = req.getSession();
				    session.setAttribute("authenticated", true);
				    session.setAttribute("userEmail", email);
				    resp.sendRedirect("AdminAdd.jsp");
					/*
					 * RequestDispatcher rd = req.getRequestDispatcher("./AdminAdd.jsp");
					 * rd.forward(req, resp);
					 */
//					resp.sendRedirect("AdminAdd.jsp");
				}else if(roll.equals("User")) {
					HttpSession session = req.getSession();
				    session.setAttribute("authenticated", true);
				    session.setAttribute("userEmail", email);
					RequestDispatcher rd = req.getRequestDispatcher("./UserButton.jsp");
					rd.forward(req, resp);
//					resp.sendRedirect("UserPage.jsp");
				}else {
					RequestDispatcher rd = req.getRequestDispatcher("./login.jsp");
					rd.forward(req, resp);
//					resp.sendRedirect("login.jsp");
				}
			}
		}
	}
}
