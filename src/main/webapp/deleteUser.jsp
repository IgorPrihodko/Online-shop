<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete user</title>
</head>
<body>
<center>
<form action="/deleteUser" method="post">
    ID <input name="id" type="number" value="<%=request.getAttribute("id") == null ?
         "" : request.getAttribute("id")%>" readonly>
    Email <input name="email" type="email" value="<%=request.getAttribute("email") == null ?
         "" : request.getAttribute("email")%>" readonly/>
    Password <input name="password" type="password" value="<%=request.getAttribute("password") == null ?
         "" : request.getAttribute("password")%>" readonly/>
    <button type="submit">Delete</button>
</form>
<form action="/users" method="get">
    <button type="submit">Back</button>
</form>
</center>
</body>
</html>
