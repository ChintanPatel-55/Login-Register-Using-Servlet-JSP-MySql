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
				    resp.sendRedirect("AdminAdd.jsp");
					/*
					 * RequestDispatcher rd = req.getRequestDispatcher("./AdminAdd.jsp");
					 * rd.forward(req, resp);
					 */
//					resp.sendRedirect("AdminAdd.jsp");
				}else if(roll.equals("User")) {
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
//	 @SuppressWarnings("unused")
//	private void setUserDetails(HttpServletRequest req, String email) {
//	        String query = "SELECT * FROM Registration WHERE email = ?";
//	        try (Connection con = DButil.getConnection();
//	             PreparedStatement pst = con.prepareStatement(query)) {
//	            pst.setString(1, email);
//
//	            try (ResultSet rs = pst.executeQuery()) {
//	                if (rs.next()) {
//	                    // Set each user detail as a request attribute
//	                    req.setAttribute("userId", rs.getInt("id"));
//	                    req.setAttribute("fname", rs.getString("fname"));
//	                    req.setAttribute("lname", rs.getString("lname"));
//	                    req.setAttribute("email", rs.getString("email"));
//	                    req.setAttribute("phone", rs.getString("phone"));
////	                    req.setAttribute("address", rs.getString("userRoll"));
//	                }
//	            }
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	      }
//	 }
}
