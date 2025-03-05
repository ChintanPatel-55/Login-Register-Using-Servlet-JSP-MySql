<% response.setHeader("Cache-Control","no-cache"); //HTTP 1.1 
		response.setHeader("Cache-Control", "no-store");
 		response.setHeader("Pragma","no-cache"); //HTTP 1.0 
 		response.setDateHeader ("Expires", 0); //prevents caching at the proxy server  
		%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Add Page</title>
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom CSS -->
<style>
    body {
        background-color: #f8f9fa;
        font-family: Arial, sans-serif;
        padding: 20px;
    }
    .container {
        max-width: 800px;
        margin: 0 auto;
        text-align: center;
    }
    .links {
        margin-bottom: 20px;
    }
    .links a {
        display: inline-block;
        margin: 10px;
        padding: 10px 20px;
        background-color: #007bff;
        color: white;
        text-decoration: none;
        border-radius: 5px;
        transition: background-color 0.3s ease;
    }
    .links a:hover {
        background-color: #0056b3;
    }
    .back-link {
        margin-top: 20px;
    }
    .back-link a {
        color: #007bff;
        text-decoration: none;
        font-weight: bold;
    }
    .back-link a:hover {
        text-decoration: underline;
    }
    h1 {
        color: #343a40;
        margin-bottom: 20px;
    }
</style>
</head>
<body>
    <div class="container">
        <h1>Admin Add Page</h1>
        <div class="links">
            <a href="DataShow.jsp" class="btn btn-primary">Show User</a>
        </div>
        <div class="back-link">
            <p><a href="login.html">Back to Login</a></p>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>