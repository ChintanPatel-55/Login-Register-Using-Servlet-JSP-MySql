<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Data Show</title>
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
    body {
        padding: 20px;
        background-color: #f8f9fa;
    }
    h1 {
        text-align: center;
        margin-bottom: 20px;
        color: #343a40;
    }
    table {
        width: 100%;
        margin-bottom: 20px;
        border-collapse: collapse;
    }
    th, td {
        padding: 12px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }
    th {
        background-color: #007bff;
        color: white;
    }
    tr:hover {
        background-color: #f1f1f1;
    }
    .action-buttons {
        display: flex;
        gap: 10px;
    }
    .btn {
        padding: 5px 10px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }
    .btn-update {
        background-color: #28a745;
        color: white;
    }
    .btn-delete {
        background-color: #dc3545;
        color: white;
    }
    .btn-add {
        background-color: #17a2b8;
        color: white;
        margin-bottom: 20px;
    }
</style>
</head>
<body>

<div class="container">
    <h1>User Data</h1>
    <!-- Add User Button -->
    <a href="AdminPage.jsp" class="btn btn-add">Add User</a>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
        <%

        String url = "jdbc:mysql://localhost:3306/Login_Regi_DB";
        String username = "root";
        String passwordd = "1234";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, passwordd);
            statement = connection.createStatement();
            String query = "select id, fname,lname, email, phone from Registration";
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("fname");
                String lname = resultSet.getString("lname");
                String email = resultSet.getString("email");
                long phone = resultSet.getLong("phone");
        %>
            <tr>
                <td><%= id %></td>
                <td><%= name %></td>
                <td><%= lname %></td>
                <td><%= email %></td>
                <td><%= phone %></td>
                <td>
                    <div class="action-buttons">
                        <!-- Update Button -->
                        <a href="UpdateTheUser.jsp" class="btn btn-update" 
                        	onclick="fillUpdateForm('<%=name %>', '<%=lname %>','<%=email %>','<%=phone %>')">Update</a>
                        <!-- Delete Button -->
                        <a href="DeleteUser.jsp?id=<%= id %>" class="btn btn-delete">Delete</a>
                    </div>
                </td>
            </tr>
        <%
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        %>
        </tbody>
    </table>
</div>

<script>
    
    function fillUpdateForm(name, lname, email, phone) {
        document.getElementById('updateName').value = name;
        document.getElementById('updatelName').value = lname;
        document.getElementById('updateEmail').value = email;
        document.getElementById('updatePhone').value = phone;
    }
</script>


<!-- Bootstrap JS and dependencies -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>