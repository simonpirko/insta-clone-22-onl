<%--
  Created by IntelliJ IDEA.
  User: Алёна
  Date: 26.09.2023
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
</head>
<body>

<div class="container">
    <div class="row">
        <c:forEach items="${postList}" var="post">

            <div class="card" style="width: 18rem;">
                <img src="data:image/jpg;base64, ${post.getPhoto()}," class="card-img-top" alt="">
                <h1>Author: ${post.getUser()}</h1>
                <h1> Description: ${post.getDescription}</h1>
            </div>
        </c:forEach> image/jpg;base64
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N"
        crossorigin="anonymous"></script>
</body>
</html>