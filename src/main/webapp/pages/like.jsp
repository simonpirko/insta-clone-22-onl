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



    </div>
</div>

<from action="/like" method="post">
<%--    <input type="button" name=""--%>
    <c:if test="${likeFlag}">

            <h1>Like!</h1>


    </c:if>


</from>


</body>
</html>
