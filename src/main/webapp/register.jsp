<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<center>
    <form action="/register" method="post">
        Email <input name="email" type="email" value="${email}"/>
        Password <input name="password" type="password"/>
        Repeat password <input name="repeatPassword" type="password"/>
        <button type="submit">Register</button>
    </form>
    <form action="/" method="get">
        <button type="submit">Exit</button>
    </form>
    <h2>${error}</h2>
</center>
</body>
</html>
