<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Registration</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>

<body>
<jsp:include page="_header.jsp"/>
<div class="row justify-content-center">
    <div class="col-3">
    <form class="mt-3 needs-validation" action="/register" method="post">
                <div class="row mb-3">
                    <div class="col">
                        <label for="name" class="form-label">Name</label>
                        <input name="name" type="text" class="form-control" id="name" required pattern="(^[A-Za-z]{3,16})">
                    </div>
                </div>
                <div class="mb-3">
                    <div class="col">
                         <label for="surname" class="form-label">Surname</label>
                         <input name="surname" type="text" class="form-control" id="surname" required pattern="(^[A-Za-z]{3,16})">
                    </div>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email address</label>
                    <input name="email" type="email" class="form-control" id="email" required pattern="^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$">
                </div>
                <div class="mb-3">
                  <label for="country" class="form-label">Country</label>
                  <select class="form-select" id="country" name="country">
                      <c:forEach var="country" items="${countries}">
                          <option value="${country.getId()}">${country.getName()}</option>
                      </c:forEach>
                  </select>
                </div>
                <div class="mb-3" >
                  <label class="input-group-text" for="photo">Photo</label>
                  <input type="file" class="form-control" id="photo" >
                </div>
                <div class="mb-3">
                    <label for="username" class="form-label">Username</label>
                    <input name="username" type="text" class="form-control" id="username" required pattern="\w*">
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input name="password" type="password" class="form-control" id="password" required pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$">
                </div>
                <div class="d-grid gap-2 col-6 mx-auto">
                    <button class="btn btn-dark" type="submit">Sign In</button>
                </div>

                <p class="fs-6">    </p>

                <p class="text-body-secondary">
                    Already have an account? <a href="/login">Log in</a>
                </p>

            </form>
        </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</body>
</html>