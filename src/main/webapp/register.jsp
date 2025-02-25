<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
<meta charset="UTF-8">
<title>Register Page</title>
</head>
<body>
<div class="container">
        <h1>Register</h1>
        <form action="RegisterServlet" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required><br>
            <label for="email">Email:</label>
            <input type="text" id="username" name="email" required><br>   
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required><br>
            <div class="some-class">
            <label for="username">Choose your roll:</label>
            <label><input type="radio" name="option" value="Admin">Admin</label>
            <label><input type="radio" name="option" value="User"> User</label>
            </div>
            <button type="submit">Register</button>
        </form>
    
        <p><a href="Index.html">Back to Home</a></p>
      </div>
</body>
</html>