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
        <form class="mt-5 w-25" action="/settings" method="post">
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
            <div class="input-group mb-3">
                <button class="btn btn-outline-secondary" type="button" id="4">Button</button>
                <input type="file" name="photo" class="form-control" id="inputGroupFile03" aria-describedby="4" aria-label="Upload">
            </div>
            <div class="form-floating mb-3">
                <input type="email" name="email" class="form-control" id="5" placeholder="name4@example.com">
                <label for="5">Email address</label>
            </div>
            <div class="form-floating mb-3">
                <input type="password"  name="password" class="form-control" id="6" placeholder="Password">
                <label for="6">Password</label>
            </div>
            <div class="form-floating mb-3">
                <input type="text" name="surname" class="form-control" id="7" placeholder="name5@example.com">
                <label for="7">Country Id</label>
            </div>
                <button type="submit" class="btn btn-primary mt-5">Save changes</button>
        </form>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</body>
</html>
