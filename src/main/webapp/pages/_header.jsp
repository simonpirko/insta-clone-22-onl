<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Header</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg bg-dark justify-content-center sticky-top" data-bs-theme="dark">
    <div class="container justify-content-center">
        <div class="container-fluid d-flex align-items-center">
            <a class="navbar-brand fst-italic fw-bolder fs-4" href="/">Insta-clone-22-onl</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                          data-bs-target="#navbarSupportedContent"
                          aria-controls="navbarSupportedContent" aria-expanded="false">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <!-- тут будут навигационные ссылки в заголовке в зависимости от наличия или отсутствия регистрации юзера -->
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                      <c:if test="${sessionScope.user != null}">
                        <a class="nav-link" aria-current="page" href="/#">Home</a>   <%-- Вставить ссылку на страницу по готовности! --%>
                      </c:if>
                    </ul>

                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                              <c:if test="${sessionScope.user != null}">
                                <a class="nav-link" aria-current="page" href="/#">Create post</a>    <%-- Вставить ссылку на страницу по готовности! --%>
                              </c:if>
                    </ul>

                    <ul class="nav justify-content-end">
                              <c:if test="${sessionScope.user == null}">
                                <li class="nav-item">
                                  <a class="nav-link" aria-current="page" href="/register">Register</a>
                                </li>
                                <li class="nav-item">
                                  <a class="nav-link" aria-current="page" href="/login">Log in</a>
                                </li>
                              </c:if>

                              <c:if test="${sessionScope.user != null}">
                                <li class="nav-item">
                                  <a class="nav-link" aria-current="page" href="/logout">Log out</a>
                                </li>
                              </c:if>

                    </ul>
            </div>
        </div>
    </div>
</nav>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</body>
</html>