<%@ page import="java.util.Map" %>
<%@ page import="model.User" %>
<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: black
  Date: 05.07.19
  Time: 2:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Users</title>
</head>
<body>
    <%
        PrintWriter printWriter2 = response.getWriter();
        printWriter2.write("<center>");
        printWriter2.write("<a href=\"/register\">Register new user</a>");
        printWriter2.write("</center>");
    %>

    <%
        PrintWriter printWriter = response.getWriter();
        printWriter.write("<center>");
        printWriter.write("<table border=\"1\">\n" +
                "    <h2>All users list</h2>\n" +
                "    <tr>\n" +
                "        <th>ID</th>\n" +
                "        <th>Email</th>\n" +
                "        <th>Password</th>\n" +
                "    </tr>");
        Map<Long, User> allUsers = (Map<Long, User>) request.getAttribute("allUsers");
        for (Long id : allUsers.keySet()) {
            printWriter.write("<tr>");
            printWriter.write("<td>" + allUsers.get(id).getId() + "</td>");
            printWriter.write("<td>" + allUsers.get(id).getEmail() + "</td>");
            printWriter.write("<td>" + allUsers.get(id).getPassword() + "</td>");
            printWriter.write("</tr>");
        }
        printWriter.write("</center>");
        printWriter.write("</table>");
    %>

</body>
</html>
