<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="styles1.css">
</head>
<body>
    
    <form action="LogoutServlet" method="get">
		<% response.setHeader("Cache-Control","no-cache"); //HTTP 1.1 
		response.setHeader("Cache-Control", "no-store");
 		response.setHeader("Pragma","no-cache"); //HTTP 1.0 
 		response.setDateHeader ("Expires", 0); //prevents caching at the proxy server  
		%>
        <button type="submit">Logout</button>
    </form>
</body>
</html>
