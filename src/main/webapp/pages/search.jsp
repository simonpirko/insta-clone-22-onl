<%@ page import="java.util.Properties" %><%--
  Created by IntelliJ IDEA.
  User: Nastya
  Date: 26.09.2023
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
    Properties properties = (Properties) request.getAttribute("properties");
    String title = properties.getProperty("search.title");
    String username = properties.getProperty("search.username");
    String button = properties.getProperty("search.button");
%>

<html>
<head>
    <title><%=title%></title>
</head>
<body>

<form action="/search" method="post">
    <input type="text" name = "OtherUsername" placeholder=<%=username%>>
    <button><%=button%></button>

    <ul>
        <c:forEach var="user" items="${users}">

            <li> ${user.getUsername()} </li>

        </c:forEach>

    </ul>

</form>

<p>${message}</p>

</body>
</html>
