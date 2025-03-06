package com.pr.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class DeleteAddressServlet
 */
@WebServlet("/DeleteAddressServlet")
public class DeleteAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;   
       
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int userId = Integer.parseInt(req.getParameter("userId"));
        int addId = Integer.parseInt(req.getParameter("addId"));
	    
	    Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
        	
        	// Establish DB connection
            String url = "jdbc:mysql://localhost:3306/Login_Regi_DB";
            String username = "root";
            String password = "1234";
            connection = DriverManager.getConnection(url, username, password);
            
            String query = "delete from addresses where id = ? and add_id  = ?";
            preparedStatement = connection.prepareStatement(query);
            
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, addId);
         int result = preparedStatement.executeUpdate();
            
            if(result > 0) {
            	System.out.println("Address Delete sucessfully");
            	resp.sendRedirect("DataShow.jsp");
            }else {
				System.out.println("Faild to Delete addresss");
			}
            
            
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	}
}
