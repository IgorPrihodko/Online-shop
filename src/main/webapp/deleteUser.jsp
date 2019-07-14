<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete user</title>
</head>
<body>
<center>
<form action="/deleteUser" method="post">
    ID <input name="id" type="number" value="${id}" readonly>
    Email <input name="email" type="email" value="${email}" readonly/>
    Password <input name="password" type="password" value="${password}" readonly/>
    <button type="submit">Delete</button>
</form>
<form action="/users" method="get">
    <button type="submit">Back</button>
</form>
</center>
</body>
</html>
