package com.pr.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.catalina.User;

@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        System.out.println("Received ID: " + id);  // Debugging line

        try {
            // Database connection setup
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/Login_Regi_DB";
            String username = "root";
            String passwordd1 = "1234";
            Connection connection = DriverManager.getConnection(url, username, passwordd1);

            // SQL Query to fetch user details along with addresses
            String query = "SELECT u.id, u.fname, u.lname, u.email, u.phone,u.password" +
                           "COALESCE(GROUP_CONCAT(a.address SEPARATOR ', '), '') AS addresses " +
                           "FROM Registration u LEFT JOIN addresses a ON u.id = a.id " +
                           "WHERE u.id = ? GROUP BY u.id";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String fname = resultSet.getString("fname");
                String lname = resultSet.getString("lname");
                String email = resultSet.getString("email");
                long phone = resultSet.getLong("phone");
                String passwordd = resultSet.getString("password");
                String addresses = resultSet.getString("addresses");

                System.out.println("Fetched Data: " + fname + ", " + lname + ", " + email+", "+", "+phone+", "+addresses);  

                // Set the retrieved data into request attributes
                req.setAttribute("id", id);
                req.setAttribute("fname", fname);
                req.setAttribute("lname", lname);
                req.setAttribute("email", email);
                req.setAttribute("phone", phone);
                req.setAttribute("password", passwordd);
                req.setAttribute("addresses", addresses);
            } else {
                System.out.println("No user found for ID: " + id);  // Debugging line
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Forward the request to UpdateTheUser.jsp
        req.getRequestDispatcher("UpdateTheUser.jsp").forward(req, resp);
    }
    
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");
        String[] addresses = req.getParameterValues("address_line");

        try {
            // Database connection setup
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/Login_Regi_DB";
            String username = "root";
            String passwordd1 = "1234";
            Connection connection = DriverManager.getConnection(url, username, passwordd1);

            // Update user info
            String updateQuery = "UPDATE Registration SET fname = ?, lname = ?, password = ?, phone = ? WHERE email = ?";
            PreparedStatement updateStmt = connection.prepareStatement(updateQuery);
            updateStmt.setString(1, fname);
            updateStmt.setString(2, lname);
            updateStmt.setString(3, password);
            updateStmt.setString(4, email);
            updateStmt.setNString(4, phone);
            int rowsUpdated = updateStmt.executeUpdate();

//             Handle address updates
            if (addresses != null && addresses.length > 0) {
                // Optionally clear old addresses
//                String deleteAddressQuery = "DELETE FROM addresses WHERE id = (SELECT id FROM Registration WHERE email = ?)";
//                PreparedStatement deleteStmt = connection.prepareStatement(deleteAddressQuery);
//                deleteStmt.setString(1, email);
//                deleteStmt.executeUpdate();

                // Insert new addresses
                String insertAddressQuery = "INSERT INTO addresses(id, address) VALUES ((SELECT id FROM Registration WHERE email = ?), ?)";
                PreparedStatement insertStmt = connection.prepareStatement(insertAddressQuery);
                for (String address : addresses) {
                    insertStmt.setString(1, email);
                    insertStmt.setString(2, address);
                    insertStmt.addBatch();
                }
                insertStmt.executeBatch();
            }

            if (rowsUpdated > 0) {
                resp.sendRedirect("UserButton.jsp");
                System.out.println("Success");
            } else {
                System.out.println("Update failed.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
