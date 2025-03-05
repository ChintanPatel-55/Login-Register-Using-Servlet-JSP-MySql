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
<title>Insert title here</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        background-color: #f4f4f4;
    }
    .container {
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        width: 300px;
        text-align: center;
    }
    h3 {
        color: #333;
        font-size: 24px;
        margin-bottom: 20px;
    }
    input[type="email"] {
        width: 100%;
        padding: 10px;
        margin: 10px 0;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }
    input[type="email"]:focus {
        border-color: #4CAF50;
        outline: none;
    }
    input[type="submit"] {
        width: 100%;
        padding: 10px;
        background-color: #4CAF50;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }
    input[type="submit"]:hover {
        background-color: #45a049;
    }
</style>
</head>
<body>

<div class="container">
    <h3>Enter Your Email</h3>
    <form action="DeleteServlet" method="post">
        <input type="email" name="email" placeholder="Enter your email" required>
        <input type="submit" value="Submit">
    </form>
</div>

</body>
</html>
