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
import java.sql.ResultSet;

/**
 * Servlet implementation class AddressIdServlet
 */
@WebServlet("/AddressIdServlet")
public class AddressIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
		
	
	
		@Override
			protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			String address = req.getParameter("address");
			
			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver");
	            String url = "jdbc:mysql://localhost:3306/Login_Regi_DB";
	            String username = "root";
	            String passwordd1 = "1234";
	            Connection connection = DriverManager.getConnection(url, username, passwordd1);
				
	            String query = "select add_id from addresses where address = ?";
	            PreparedStatement preparedStatement= connection.prepareStatement(query);
	            preparedStatement.setString(1, address);
	            ResultSet resultSet = preparedStatement.executeQuery();
	            
	            if(resultSet.next()) {
	            	int add_id = resultSet.getInt("add_id");
	            	req.setAttribute("addressID", String.valueOf(add_id));
	            }else {
					req.setAttribute("addressID", "Address Not Found");
				}
				
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			req.getRequestDispatcher("DataShow.jsp").forward(req, resp);
			
		}
}
