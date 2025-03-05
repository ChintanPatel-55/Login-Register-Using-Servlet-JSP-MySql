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
import java.sql.SQLException;

/**
 * Servlet implementation class AddAddressServlet
 */
@WebServlet("/AddAddressServlet")
public class AddAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
    	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	
    	// Get the user ID and new addresses from the form
        String userId = req.getParameter("userId");
        String[] newAddresses = req.getParameterValues("newAddresses");

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Establish DB connection
            String url = "jdbc:mysql://localhost:3306/Login_Regi_DB";
            String username = "root";
            String password = "1234";
            connection = DriverManager.getConnection(url, username, password);

            // Insert new addresses for the user
            if (newAddresses != null && newAddresses.length > 0) {
                String query = "INSERT INTO addresses (id, address) VALUES (?, ?)";
                preparedStatement = connection.prepareStatement(query);

                for (String address : newAddresses) {
                    preparedStatement.setInt(1, Integer.parseInt(userId)); // Set the user ID
                    preparedStatement.setString(2, address); // Set the address
                    preparedStatement.addBatch(); // Add batch for efficient processing
                }

                int[] result = preparedStatement.executeBatch(); // Execute the batch of insert queries

                if (result.length > 0) {
                    
                	System.out.println("Address added sucessfully");
                	resp.sendRedirect("DataShow.jsp");
                } else {
                    System.out.println("Faild to Update addresss");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("error.jsp"); // Redirect to an error page
        } 
    	
    	
    	}
}
