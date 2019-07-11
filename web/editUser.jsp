<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: black
  Date: 09.07.19
  Time: 1:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit user</title>
</head>
<body>
<center>
    <form action="/editUser" method="post">
        ID <input name="id" type="number" value="<%=request.getAttribute("id") == null ?
         "" : request.getAttribute("id")%>" readonly>
        Email <input name="email" type="email" value="<%=request.getAttribute("email") == null ?
         "" : request.getAttribute("email")%>"/>
        Password <input name="password" type="password"/>
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
