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

import com.pr.util.DButil;

/**
 * Servlet implementation class UserButtonServlet
 */
@WebServlet("/UserButtonServlet")
public class UserButtonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		HttpSession session = req.getSession();
        String email = (String) session.getAttribute("userEmail");
        
        if (email == null) {
            resp.sendRedirect("login.jsp"); // Session expired
            return;
        }

        try (Connection conn = DButil.getConnection()) {
            String sql = "SELECT u.id, u.fname, u.lname, u.email, u.phone, " +
                    "COALESCE(GROUP_CONCAT(a.address SEPARATOR ', '), '') AS addresses " +
                    "FROM Registration u LEFT JOIN addresses a ON u.id = a.id " +
                    "WHERE u.email = ? GROUP BY u.email";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Set user data as request attributes
                req.setAttribute("userId", rs.getInt("id"));
                req.setAttribute("fname", rs.getString("fname"));
                req.setAttribute("lname", rs.getString("lname"));
                req.setAttribute("email", rs.getString("email"));
                req.setAttribute("phone", rs.getString("phone"));
                req.setAttribute("addresses", rs.getString("addresses"));
                
               
                System.out.println("Adress: " + rs.getString("addresses"));
                
                // Forward to JSP
                RequestDispatcher dispatcher = req.getRequestDispatcher("/UserPage.jsp");
                dispatcher.forward(req, resp);
            } else {
                resp.sendRedirect("UserButton.jsp"); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendRedirect("Index.jsp");
        }
		
	}

}
