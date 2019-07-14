<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Title</title>
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
    <h2>${error}</h2>
</center>
</body>
</html>
