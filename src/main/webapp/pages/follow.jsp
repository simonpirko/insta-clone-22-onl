<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 29/10/2023
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Follow</title>
</head>
<form action="/follow" method="post">
    <input type="hidden" name="userId" value="${user.id}">
    <input type="hidden" name="username" value="${user.username}">

    <button>Follow</button>
</form>
<form action="/unfollow" method ="delete">
    <delete type ="hidden" name="userId" value="${user.id}">
        </delete type="hidden" name="username" value="${user.username}">
        <button>Unfollow</button>
</form>
<body>

</body>
</html>
