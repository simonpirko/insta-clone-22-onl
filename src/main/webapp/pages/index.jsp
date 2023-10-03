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
<%@include file="_header.jsp"%>


<div class="container">
    <div class="row justify-content-center">
        <div class="col-5 mt-3 justify-content-center">
            <ul>
                <c:forEach items="${postList}" var="item">
                    <div class="card mt-5" style="width: 30rem;">
                        <p style="margin-left: 20px">
                            <a href="/user/profile?username=${item.getUser().getUsername()}"
                               class="link-body-emphasis link-offset-2 link-underline-opacity-25 link-underline-opacity-75-hover fs-5 fw-bold font-monospace">${item.getUser().getUsername()}
                            </a>
                        </p>

                        <a href="/user/viewpost?id=${item.getId()}">
                            <img src="data:image/jpg;base64,${item.getPhoto()}" width="100%"/>
                        </a>

                        <div class="card-body">
                            <p class="card-text">${item.getDescription()}</p>
                        </div>
                    </div>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
</body>
</html>