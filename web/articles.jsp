<%@ page import="model.User" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.Map" %>
<%@ page import="model.Article" %><%--
  Created by IntelliJ IDEA.
  User: black
  Date: 06.07.19
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Articles</title>
</head>
<body>

    <%
        PrintWriter printWriter = response.getWriter();
        printWriter.write("<center>");
        printWriter.write("<table border=\"1\">\n" +
                "    <h2>All articles list</h2>\n" +
                "    <tr>\n" +
                "        <th>ID</th>\n" +
                "        <th>Title</th>\n" +
                "        <th>Description</th>\n" +
                "        <th>Price</th>\n" +
                "    </tr>");
        Map<Long, Article> allArticles = (Map<Long, Article>) request.getAttribute("allArticles");
        for (Long id : allArticles.keySet()) {
            printWriter.write("<tr>");
            printWriter.write("<td>" + allArticles.get(id).getId() + "</td>");
            printWriter.write("<td>" + allArticles.get(id).getTitle() + "</td>");
            printWriter.write("<td>" + allArticles.get(id).getDescription() + "</td>");
            printWriter.write("<td>" + allArticles.get(id).getPrice() + "</td>");
            printWriter.write("</tr>");
        }
        printWriter.write("</center>");
        printWriter.write("</table>");
    %>

</body>
</html>
