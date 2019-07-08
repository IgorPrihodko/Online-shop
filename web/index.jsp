<%--
  Created by IntelliJ IDEA.
  User: black
  Date: 03.07.19
  Time: 19:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>$Title$</title>
</head>
<body>
  <center>
    Welcome to this online-Store!
  </center>
  <center>
    You can register here --->
    <a href="/register">Registration</a>
  </center>
  <center>
  <center>
    Or you can signing in
  </center>
  <form action="/signIn" method="post">
    <input name="email" placeholder="Email" type="email"/>
    <input name="password" placeholder="Password" type="password"/>
    <button type="submit">Sign in</button>
  </form>
</center>
</body>
</html>
