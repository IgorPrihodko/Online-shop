<%@ page import="model.Product" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: black
  Date: 06.07.19
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All products</title>
</head>
<body>
    <%
        List<Product> allProducts = (List<Product>) request.getAttribute("allProducts");
        PrintWriter printWriter1 = response.getWriter();
        printWriter1.write("<center>");
        printWriter1.write("<a href=\"/addProduct\">Add new product</a>");
        printWriter1.write("</center>");
    %>

    <%

        PrintWriter printWriter2 = response.getWriter();
        printWriter2.write("<center>");
        printWriter2.write("<table border=\"1\">\n" +
                "    <h2>All products list</h2>\n" +
                "    <tr>\n" +
                "        <th>ID</th>\n" +
                "        <th>Title</th>\n" +
                "        <th>Description</th>\n" +
                "        <th>Price</th>\n" +
                "        <th>Edit Product</th>\n" +
                "        <th>Delete product</th>\n" +
                "    </tr>");
        for (Product product : allProducts) {
            printWriter2.write("<tr>");
            printWriter2.write("<td>" + product.getId() + "</td>");
            printWriter2.write("<td>" + product.getTitle() + "</td>");
            printWriter2.write("<td>" + product.getDescription() + "</td>");
            printWriter2.write("<td>" + product.getPrice() + "</td>");
            printWriter2.write("<td>" + "<center>" + "<a href=\"/editProduct?id=" + product.getId() +
                    "\">Edit</a>" + "</center>" + "</td>");
            printWriter2.write("<td>" + "<center>" + "<a href=\"/deleteProduct?id=" + product.getId() +
                    "\">Delete</a>" + "</center>" + "</td>");
            printWriter2.write("</tr>");
        }
        printWriter2.write("</center>");
        printWriter2.write("</table>");
    %>

    <%
        PrintWriter printWriter3 = response.getWriter();
        printWriter3.write("<center>");
        printWriter3.write("<a href=\"/users\">List of all users</a>");
        printWriter3.write("</center>");
    %>

    <%
        PrintWriter printWriter4 = response.getWriter();
        printWriter4.write("<form action=\"/\" method=\"get\">\n" +
                "        <button type=\"submit\">Exit</button>\n" +
                "    </form>");
    %>
</body>
</html>
