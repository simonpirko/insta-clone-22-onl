<%--
  Created by IntelliJ IDEA.
  User: Nastya
  Date: 26.09.2023
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search</title>
</head>
<body>

<form action="/search" method="post">
    <input type="text" name = "username" placeholder="Username">
    <button>Search</button>

</form>

<h3></h3>
<p>${message}</p>

</body>
</html>
