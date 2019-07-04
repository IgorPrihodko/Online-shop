<%--
  Created by IntelliJ IDEA.
  User: black
  Date: 03.07.19
  Time: 23:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<center>
    <form action="/register" method="post">
        Email <input name="email" type="email"/>
        Password <input name="password" type="password"/>
        Repeat password <input name="repeatPassword" type="password"/>
        <button type="submit">Register</button>
    </form>
</center>
</body>
</html>
