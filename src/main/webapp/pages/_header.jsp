<%--
  Created by IntelliJ IDEA.
  User: ilyamoiseenko
  Date: 23.09.23
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary justify-content-center sticky-top" data-bs-theme="dark">
    <div class="container justify-content-center">
        <div class="container-fluid d-flex align-items-center">

            <a class="navbar-brand fst-italic fw-bolder fs-4" href="/">Instagram</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <c:if test="${user != null}">
                        <a class="nav-link" aria-current="page" href="/">Home</a>
                    </c:if>
                </ul>

                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <c:if test="${user != null}">
                        <a class="nav-link" aria-current="page" href="/create-post">Create post</a>
                    </c:if>
                </ul>

                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <c:if test="${user != null}">
                        <c:url value="/user/profile" var="profileURL">
                            <c:param name="username" value="${sessionScope.user.username}"/>
                        </c:url>

                        <a class="nav-link" aria-current="page" href='<c:out value="${profileURL}" />'>Profile</a>
                    </c:if>
                </ul>


                <ul class="nav justify-content-end">
                    <c:if test="${user == null}">
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="/register">Register</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="/login">Log in</a>
                        </li>
                    </c:if>

                    <c:if test="${user != null}">
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="/logout">Log out</a>
                        </li>
                    </c:if>

                </ul>
            </div>
        </div>
    </div>
</nav>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

</body>
</html>
