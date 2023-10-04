<%--
  Created by IntelliJ IDEA.
  User: vvvvv
  Date: 24.09.2023
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Settings</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <form class="mt-5 w-25" action="/settings" method="post" enctype="multipart/form-data">
            <div class="form-floating mb-3">
                <input type="text" name="name" class="form-control" id="1" placeholder="name@example.com">
                <label for="1">First name</label>
            </div>
            <div class="form-floating mb-3">
                <input type="text" name="surname" class="form-control" id="2" placeholder="name2@example.com">
                <label for="2">Second name</label>
            </div>
            <div class="form-floating mb-3">
                <input type="text" name="username" class="form-control" id="3" placeholder="name3@example.com">
                <label for="3">User name</label>
            </div>
            <div class="mb-3" >
                <label class="form-label" for="4"></label>
                <input type="file" class="form-control" id="4" name="photo" >
            </div>
            <div class="mb-3">
                <label for="5" class="form-label"></label>
                <select class="form-select" id = "5" name="country" aria-label="Default select example">
                    <option selected>Country</option>
                    <c:forEach var="country" items="${countries}">
                        <option value="${country.getId()}">${country.getName()}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-floating mb-3">
                <input type="email" name="email" class="form-control" id="6" placeholder="name4@example.com">
                <label for="6">Email address</label>
            </div>
            <div class="form-floating mb-3">
                <input type="password"  name="password" class="form-control" id="7" placeholder="Password">
                <label for="7">Password</label>
            </div>
            <div class="d-grid gap-2 col-6 mx-auto">
                <button class="btn btn-dark" type="submit">Save changes</button>
            </div>
        </form>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</body>
</html>
