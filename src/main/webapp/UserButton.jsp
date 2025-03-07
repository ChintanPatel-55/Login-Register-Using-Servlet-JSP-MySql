<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Button</title>
    <style>
        /* Centering the button */
        .center-button {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        button {
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
        }
    </style>
</head>
<body>

    <div class="center-button">
        <a href="UserButtonServlet">
            <button>Edit</button>
        </a>
    </div>

</body>
</html>
