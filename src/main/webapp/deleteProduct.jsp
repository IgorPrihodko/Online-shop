<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete product</title>
</head>
<body>
<center>
    <form action="/deleteProduct" method="post">
        ID <input name="id" type="number" value="<%=request.getAttribute("id") == null ?
         "" : request.getAttribute("id")%>" readonly>
        Title <input name="title" type="text" value="<%=request.getAttribute("title") == null ?
         "" : request.getAttribute("title")%>" readonly/>
        Description <input name="description" type="text" value="<%=request.getAttribute("description") == null ?
         "" : request.getAttribute("description")%>" readonly/>
        Price <input name="price" type="number" value="<%=request.getAttribute("price") == null ?
         "" : request.getAttribute("price")%>" readonly/>
        <button type="submit">Delete</button>
    </form>
    <form action="/products" method="get">
        <button type="submit">Back</button>
    </form>
</center>
</body>
</html>
