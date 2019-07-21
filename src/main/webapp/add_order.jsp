<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order</title>
</head>
<body>
<div align="center">
    <form action="/user/order" method="post">
        User ID <input name="userID" type="number" value="${userID}" readonly/>
        Email <input name="userEmail" type="email" value="${userEmail}"readonly/>
        Name <input name="name" type="text" value="${name}"/>
        Surname <input name="surname" type="text" value="${surname}"/>
        Address <input name="address" type="text" value="${address}"/>
        Confirmation code <input name="confirmationCode" type="text" value="${code}"/>
        <h3>Total price = ${totalPrice}</h3>
        <button type="submit">Buy</button>
    </form>
    <form action="/user/sendCode" method="get">
        <button type="submit">Send confirmation code to email</button>
    </form>
    <h2>${error}</h2>
    <form action="/user/basket" method="get">
        <button type="submit">Back to basket</button>
    </form>
</div>
</body>
</html>
