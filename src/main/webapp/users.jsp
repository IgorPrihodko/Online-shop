<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Users</title>
</head>
<body>
<div align="center">
    <a href="/register">Register new user</a>
</div>
<table border="1" align="center">
    <div align="center">
        <h2>All users list</h2>
    </div>
    <tr>
        <td>ID</td>
        <td>Email</td>
        <td>Password</td>
        <td>Edit user</td>
        <td>Delete user</td>
    </tr>
    <c:forEach var="user" items="${allUsers}">
        <tr>
            <td>${user.id}</td>
            <td>${user.email}</td>
            <td>${user.password}</td>
            <td><a href="/editUser?id=${user.id}">Edit</a></td>
            <td><a href="/deleteUser?id=${user.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<div align="center">
    <a href="/products">List of all products</a>
</div>
<div align="center">
    <form action="/" method="get">
        <button type="submit">Exit</button>
    </form>
</div>
</body>
</html>
