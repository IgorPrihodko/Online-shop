<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit product</title>
</head>
<body>
<center>
    <form action="/editProduct" method="post">
        ID <input name="id" type="number" value="<%=request.getAttribute("id") == null ?
         "" : request.getAttribute("id")%>" readonly>
        Title <input name="title" type="text" value="<%=request.getAttribute("title") == null ?
         "" : request.getAttribute("title")%>"/>
        Description <input name="description" type="text" value="<%=request.getAttribute("description") == null ?
         "" : request.getAttribute("description")%>"/>
        Price <input name="price" type="number" step="0.1" value="<%=request.getAttribute("price") == null ?
         "" : request.getAttribute("price")%>"/>
        <button type="submit">Edit</button>
    </form>
    <h2>${error}</h2>
    <form action="/products" method="get">
        <button type="submit">Back</button>
    </form>
</center>
</body>
</html>
