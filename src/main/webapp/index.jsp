<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
  <center>
    Welcome to this online-Store!
  </center>
  <center>
  <center>
    Sign in here
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
