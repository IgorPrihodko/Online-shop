<%--
  Created by IntelliJ IDEA.
  User: black
  Date: 04.07.19
  Time: 0:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>addArticle</title>
</head>
<body>
<center>
    <form action="/addArticle" method="post">
        Title <input name="title" type="text"/>
        Description <input name="description" type="text"/>
        Price <input name="price" type="number"/>
        <button type="submit">Add</button>
    </form>
    <form action="/" method="get">
        <button type="submit">Exit</button>
    </form>
</center>
</body>
</html>
