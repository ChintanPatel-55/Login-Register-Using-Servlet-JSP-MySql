<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0
    response.setDateHeader("Expires", 0); // Proxies
%> 
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%
    // Check if user is authenticated
    HttpSession sessionn = request.getSession(false);
    if (sessionn == null || session.getAttribute("authenticated") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Data</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
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
        .btn {
            padding: 5px 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
        }
        .btn-add {
            background-color: #17a2b8;
            color: white;
            margin-bottom: 20px;
        }
        .btn-update {
            background-color: #28a745;
            color: white;
        }
        .btn-delete {
            background-color: #dc3545;
            color: white;
        }
        .btn-success {
            background-color: #28a745;
            color: white;
        }
        .action-buttons {
            display: flex;
            gap: 10px;
        }
        .address-container {
            display: flex;
            align-items: center;
            gap: 10px;
        }
        .form-select {
            width: auto;
            min-width: 200px;
        }
        .add-icon, .minus-icon {
            cursor: pointer;
            font-size: 1.2em;
        }
        .add-icon {
            color: #28a745;
        }
        .add-icon:hover {
            color: #218838;
        }
        .minus-icon {
            color: #dc3545;
        }
        .minus-icon:hover {
            color: #c82333;
        }
        .new-address-fields {
            margin-top: 10px;
        }
        .new-address-input {
            display: flex;
            align-items: center;
            gap: 10px;
            margin-bottom: 5px;
        }
        .message {
            margin-top: 10px;
            color: green;
            font-weight: bold;
        }
        .mt-2 {
            margin-top: 0.5rem;
        }
        .address-item {
            display: flex;
            align-items: center;
            gap: 8px;
            margin: 4px 0;
            padding: 6px;
            background-color: #f8f9fa;
            border-radius: 4px;
        }
        .delete-address {
            color: #dc3545;
            cursor: pointer;
            transition: color 0.2s;
        }
        .delete-address:hover {
            color: #bb2d3b;
        }
        .address-list {
            max-height: 150px;
            overflow-y: auto;
            border: 1px solid #dee2e6;
            border-radius: 4px;
            padding: 8px;
        }
    </style>
</head>
<body>
<script>
    // Set loggedIn to true when the page loads
    sessionStorage.setItem('loggedIn', 'true');
</script>
<div class="container">
    <h1>User Data</h1>
    <a href="AdminPage.jsp" class="btn btn-add">Add User</a>
    
    <table class="table table-striped">
        <thead>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Address</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
        <% 
            Connection connection = null;
            Statement statement = null;
            ResultSet resultSet = null;
            
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Login_Regi_DB", "root", "1234");
                statement = connection.createStatement();
                String query = "SELECT c.id, c.fname, c.lname, c.email, c.phone, " +
                               "GROUP_CONCAT(CONCAT(a.add_id, ':', a.address) SEPARATOR ' , ') AS addresses " +
                               "FROM Registration c " +
                               "LEFT JOIN addresses a ON c.id = a.id " +
                               "GROUP BY c.id, c.fname, c.lname, c.email, c.phone";
                resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String fname = resultSet.getString("fname");
                    String lname = resultSet.getString("lname");
                    String email = resultSet.getString("email");
                    String phone = resultSet.getString("phone");
                    String addresses = resultSet.getString("addresses");
        %>
            <tr>
                <td><%= id %></td>
                <td><%= fname %></td>
                <td><%= lname %></td>
                <td><%= email %></td>
                <td><%= phone %></td>
                <td>
                    <div class="address-container">
                        <div class="address-list">
                            <% if (addresses != null) { 
                                for (String addressEntry : addresses.split(" , ")) { 
                                    String[] parts = addressEntry.split(":");
                                    String addId = parts[0];
                                    String address = parts.length > 1 ? parts[1] : "";
                            %>
                                <div class="address-item">
                                    <span><%= address %></span>
                                    <i class="fas fa-trash delete-address" 
                                       onclick="deleteAddress(<%= id %>, <%= addId %>)"></i>
                                </div>
                            <% }
                            } else { %>
                                <div class="text-muted">No addresses found</div>
                            <% } %>
                        </div>
                        <i class="fas fa-plus-circle add-icon" onclick="addAddressField(<%= id %>)"></i>
                    </div>
                    <form action="AddAddressServlet" method="post" id="form-<%= id %>">
                        <div class="new-address-fields" id="address-fields-<%= id %>"></div>
                        <input type="hidden" name="userId" value="<%= id %>">
                        <button type="submit" class="btn btn-success mt-2">Add Addresses</button>
                        <div class="message">
                        </div>
                    </form>
                </td>
                <td>
                    <div class="action-buttons">
                        <a class="btn btn-update" onclick="window.location.href='UpdateFileServlet?id=<%= resultSet.getInt("id") %>'">Update</a>
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
       <a href="Logout" class="btn btn-danger">Logout</a>
</div>

<script>
function addAddressField(userId) {
    const container = document.getElementById('address-fields-' + userId);
    const newField = document.createElement('div');
    newField.className = 'new-address-input mb-2';
    newField.innerHTML = `
        <input type="text" name="newAddresses" class="form-control" placeholder="New Address">
        <i class="fas fa-minus-circle minus-icon" onclick="this.parentElement.remove()"></i>
    `;
    container.appendChild(newField);
}

function deleteAddress(userId, addId) {
    if (confirm('Are you sure you want to delete this address?')) {
        fetch('DeleteAddressServlet?userId=' + userId + '&addId=' + addId)
            .then(response => {
                if (response.ok) {
                    location.reload();
                } else {
                    alert('Error deleting address');
                }
            })
            .catch(error => console.error('Error:', error));
    }
}

</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>