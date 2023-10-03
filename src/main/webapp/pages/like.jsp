<%--
  Created by IntelliJ IDEA.
  User: Nastya
  Date: 30.09.2023
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Like</title>

</head>
<body>
<jsp:include page="_header.jsp"/>

<div class="container">
    <div class="col-sm-3">

        <form action="/like" method="post">

            <button type="submit" class="btn btn-primary">
                <c:choose>
                    <c:when test="${hasLike}">
                        <i class="bi bi-heart-fill"></i>
                    </c:when>

                    <c:otherwise>
                        <i class="bi bi-heart"></i>
                    </c:otherwise>
                </c:choose>
            </button>

        </form>

    </div>
</div>




</body>
</html>
