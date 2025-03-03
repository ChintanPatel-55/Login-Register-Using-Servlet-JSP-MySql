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

@WebServlet("/UpdateFileServlet")
public class UpdateFileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
//        String phone = req.getParameter("phone");
        String password = req.getParameter("password");
//        String[] addresses = req.getParameterValues("address_line");

        try {
            // Database connection setup
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/Login_Regi_DB";
            String username = "root";
            String passwordd1 = "1234";
            Connection connection = DriverManager.getConnection(url, username, passwordd1);

            // Update user info
            String updateQuery = "UPDATE Registration SET fname = ?, lname = ?, password = ? WHERE email = ?";
            PreparedStatement updateStmt = connection.prepareStatement(updateQuery);
            updateStmt.setString(1, fname);
            updateStmt.setString(2, lname);
            updateStmt.setString(3, password);
            updateStmt.setString(4, email);
//            updateStmt.setLong(4, phone);
            int rowsUpdated = updateStmt.executeUpdate();

            // Handle address updates
//            if (addresses != null && addresses.length > 0) {
//                // Optionally clear old addresses
//                String deleteAddressQuery = "DELETE FROM addresses WHERE id = (SELECT id FROM Registration WHERE email = ?)";
//                PreparedStatement deleteStmt = connection.prepareStatement(deleteAddressQuery);
//                deleteStmt.setString(1, email);
//                deleteStmt.executeUpdate();
//
//                // Insert new addresses
//                String insertAddressQuery = "INSERT INTO addresses(id, address) VALUES ((SELECT id FROM Registration WHERE email = ?), ?)";
//                PreparedStatement insertStmt = connection.prepareStatement(insertAddressQuery);
//                for (String address : addresses) {
//                    insertStmt.setString(1, email);
//                    insertStmt.setString(2, address);
//                    insertStmt.addBatch();
//                }
//                insertStmt.executeBatch();
//            }

            if (rowsUpdated > 0) {
                resp.sendRedirect("DataShow.jsp");
                System.out.println("Success");
            } else {
                System.out.println("Update failed.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
