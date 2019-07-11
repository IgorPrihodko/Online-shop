<%@ page import="model.User" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.List" %><%--
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
        List<User> allUsers = (List<User>)request.getAttribute("allUsers");
        PrintWriter printWriter1 = response.getWriter();
        printWriter1.write("<center>");
        printWriter1.write("<a href=\"/register\">Register new user</a>");
        printWriter1.write("</center>");
    %>

    <%
        PrintWriter printWriter2 = response.getWriter();
        printWriter2.write("<center>");
        printWriter2.write("<table border=\"1\">\n" +
                "    <h2>All users list</h2>\n" +
                "    <tr>\n" +
                "        <th>ID</th>\n" +
                "        <th>Email</th>\n" +
                "        <th>Password</th>\n" +
                "        <th>Edit user</th>\n" +
                "        <th>Delete user</th>\n" +
                "    </tr>");
        for (User user : allUsers) {
            printWriter2.write("<tr>");
            printWriter2.write("<td>" + user.getId() + "</td>");
            printWriter2.write("<td>" + user.getEmail() + "</td>");
            printWriter2.write("<td>" + user.getPassword() + "</td>");
            printWriter2.write("<td>" + "<center>" + "<a href=\"/editUser?id=" + user.getId() +
                    "\">Edit</a>" + "</center>" + "</td>");
            printWriter2.write("<td>" + "<center>" + "<a href=\"/deleteUser?id=" + user.getId() +
                    "\">Delete</a>" + "</center>" + "</td>");
            printWriter2.write("</tr>");
        }
        printWriter2.write("</center>");
        printWriter2.write("</table>");
    %>

    <%
        PrintWriter printWriter3 = response.getWriter();
        printWriter3.write("<center>");
        printWriter3.write("<a href=\"/products\">List of all products</a>");
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
