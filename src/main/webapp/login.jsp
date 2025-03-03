<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<body>
<div class="container">

<h1>Login</h1>
        <form action="LoginServlet" method="post"> <!-- Change method to "post" -->
            <label for="email">Email:</label>
            <input type="text" id="email" name="email" required><br>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required><br>
            <button type="submit">Login</button>
        </form>
        
         <p><a href="Index.html">Back to Home</a></p>
        
        </div>

</body>
</html>