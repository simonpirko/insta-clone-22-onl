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

    <style>
        .pages{
            border: 1px solid;
            border-width: 1px;
        }
    </style>

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



<div class="container">
    <div class="row justify-content-center">
        <div class="col-3 mt-4 justify-content-center">
            <c:if test="${currentPage != 1}">
                <td>
                    <a href="page?page=${currentPage - 1}">

                        <button type="button" class="btn btn-primary btn-lg"
                                style="padding: unset; --bs-btn-hover-color: red; transition: 0.3s">
                            <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="grey" class="bi bi-arrow-left-square-fill" viewBox="0 0 16 16">
                                <path d="M16 14a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12zm-4.5-6.5H5.707l2.147-2.146a.5.5 0 1 0-.708-.708l-3 3a.5.5 0 0 0 0 .708l3 3a.5.5 0 0 0 .708-.708L5.707 8.5H11.5a.5.5 0 0 0 0-1z"/>
                            </svg>
                        </button>
                    </a>
                </td>
            </c:if>


<table cellpadding="5" cellspacing="5">
    <tr>
        <c:forEach begin="1" end="${numbOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <td class="pages">
                            ${i}
                    </td>
                </c:when>

                    <c:otherwise>
                        <td class="pages">
                            <a href="page?page=${i}"> ${i} </a>
                        </td>
                    </c:otherwise>
            </c:choose>
        </c:forEach>
    </tr>
</table>


                        <c:if test="${currentPage lt numbOfPages}">
                <td>
                    <a href="page?page=${currentPage + 1}">

                        <button type="button" class="btn btn-primary btn-lg"
                                style="padding: unset; --bs-btn-hover-color: red; transition: 0.3s">
                            <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="grey" class="bi bi-arrow-right-square-fill" viewBox="0 0 16 16">
                                <path d="M0 14a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2a2 2 0 0 0-2 2v12zm4.5-6.5h5.793L8.146 5.354a.5.5 0 1 1 .708-.708l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L10.293 8.5H4.5a.5.5 0 0 1 0-1z"/>
                            </svg>
                        </button>
                    </a>

                </td>

            </c:if>
        </div>
    </div>

</div>


</body>
</html>