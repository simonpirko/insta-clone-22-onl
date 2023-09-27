<%--
  Created by IntelliJ IDEA.
  User: Nastya
  Date: 26.09.2023
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Search</title>
</head>
<body>

<form action="/search" method="post">
    <input type="text" name = "OtherUsername" placeholder="Username">
    <button>Search</button>

    <c:forEach var="username" items="${username}">
        <option value="${user.getUsername()}">${username}</option>
    </c:forEach>

</form>

<h3></h3>
<p>${message}</p>

</body>
</html>
