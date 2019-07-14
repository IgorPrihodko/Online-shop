<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit user</title>
</head>
<body>
<center>
    <form action="/editUser" method="post">
        ID <input name="id" type="number" value="${id}" readonly>
        Email <input name="email" type="email" value="${email}"/>
        Password <input name="password" type="password" value="${password}"/>
        Repeat password <input name="repeatPassword" type="password"/>
        <button type="submit">Edit</button>
    </form>
    <h2>${error}</h2>
    <form action="/users" method="get">
        <button type="submit">Back</button>
    </form>
</center>
</body>
</html>
