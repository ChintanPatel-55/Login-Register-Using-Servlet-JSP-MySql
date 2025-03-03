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
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
    	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    		
    		String email = req.getParameter("email");
    		
    		try {
    			
    			 // Database connection setup
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/Login_Regi_DB";
                String username = "root";
                String passwordd1 = "1234";
                Connection connection = DriverManager.getConnection(url, username, passwordd1);
    			
//                String deleteQuery = "delete from Registration where email = ?";
                String deleteQuery = "delete from Registration, addresses using Registration inner join addresses on Registration.id = addresses.id where Registration.email = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
                
                preparedStatement.setString(1, email);
                
                int rowexe = preparedStatement.executeUpdate();
                
                if(rowexe > 0) {
                	System.out.println("Delete Successfully to the database");
                	resp.sendRedirect("DataShow.jsp");
                }else {
                	System.out.println("Does not Delete Successfully to the database");
				}
    			
    			
    		} catch (Exception e) {
    			System.out.println(e.getMessage());
    		}
    	
    	}

}
