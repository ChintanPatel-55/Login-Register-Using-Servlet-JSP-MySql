package com.pr.servlet;

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

import com.pr.util.DButil;

/**
 * Servlet implementation class LoginServlet2
 */
@WebServlet("/LoginServlet2")
public class LoginServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		String email = req.getParameter("email");
        String password = req.getParameter("password");
        
        try (Connection conn = DButil.getConnection()) {
            
            String sql = "SELECT email, userRoll FROM Registration WHERE email = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
        
                HttpSession session = req.getSession();
                session.setAttribute("userEmail", email); 
                
                String role = rs.getString("userRoll");
                if (role.equals("Admin")) {
                    resp.sendRedirect("AdminAdd.jsp");
                } else if (role.equals("User")) {
                    resp.sendRedirect("UserButton.jsp");
                }
            } else {
                resp.sendRedirect("login.jsp"); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendRedirect("Index.html");
        }
			
		
		
		
		}
}
