package com.pr.servlet;

import jakarta.servlet.RequestDispatcher;
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
 * Servlet implementation class StaticRegister
 */
@WebServlet("/StaticRegister")
public class StaticRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String fname = req.getParameter("fname");
		String lname = req.getParameter("lname");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String phone = req.getParameter("phone");
		String[] address = req.getParameterValues("address_line");
		String userRoll = req.getParameter("option");
		
		  boolean isUserInserted = false;
		  boolean areAddressesInserted = false;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/Login_Regi_DB";
			String username = "root";
			String passwordd = "1234";
			
			Connection connection = DriverManager.getConnection(url,username,passwordd);
			
			String insertRegistration = "insert into Registration(fname, lname, email, password, phone,userRoll) values(?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(insertRegistration,PreparedStatement.RETURN_GENERATED_KEYS);
	
			preparedStatement.setString(1, fname);
			preparedStatement.setString(2, lname);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, password);
			preparedStatement.setString(5, phone);
			preparedStatement.setString(6, userRoll);
			
		int row = preparedStatement.executeUpdate();
			
		if(row>0) {
			isUserInserted =true;
			
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			int userID = 0;
			
			if(resultSet.next()) {
				userID = resultSet.getInt(1);
			}
			
			if(address != null && address.length>0) {
				String query2 = "insert into addresses(id,address) values(?,?)";
				PreparedStatement addStmt =connection.prepareStatement(query2);
				
				for(String addres : address) {
					addStmt.setInt(1, userID);
					addStmt.setString(2, addres);
					addStmt.addBatch();
				}
				int[] addresRowCount  = addStmt.executeBatch();
				areAddressesInserted=addresRowCount.length==address.length;
			}
		}
		
		if(isUserInserted && areAddressesInserted ) {
			System.out.println("Sucess");
			RequestDispatcher rd = req.getRequestDispatcher("./DataShow.jsp");
			rd.forward(req, resp);
//			resp.sendRedirect("login.jsp");
		}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}	

