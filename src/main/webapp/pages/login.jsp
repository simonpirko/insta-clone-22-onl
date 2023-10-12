        <%--
  Created by IntelliJ IDEA.
  User: ilyamoiseenko
  Date: 25.09.23
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.FileInputStream" %>
<%@ page import="java.util.Properties" %>
<%@ page import="java.io.IOException" %>
        <%@ page import="java.util.Locale" %>

        <%
    String filePathEn = "C:/Users/vvvvv/AppData/Local/Temp/Temp3f1a262f-50a7-4222-b95c-284a126eb5ea_lesson-22-22-onl.zip/insta-clone-22-onl/src/main/resources/massage_eng.properties";
    String filePathRu = "C:/Users/vvvvv/AppData/Local/Temp/Temp3f1a262f-50a7-4222-b95c-284a126eb5ea_lesson-22-22-onl.zip/insta-clone-22-onl/src/main/resources/massage_rus.properties";

    Locale locale = (Locale) request.getSession().getAttribute("locale");
    String language = locale.getLanguage();
    Properties properties = new Properties();

    String username;
    String password;
    String filePath;

    if (language.equals("ru")) {
        filePath = filePathRu;
    } else {
        filePath = filePathEn;
    }

    try {
        properties.load(new FileInputStream(filePath));

        username = properties.getProperty("login.username");
        password = properties.getProperty("login.password");

    } catch (IOException e) {
        throw new RuntimeException(e);
    }
%>


<html>
<head>
  <title>Login</title>

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

</head>
<body>
<jsp:include page="_header.jsp"/>
<div class="row justify-content-center">
  <div class="col-4">
    <div class="container">
      <form action="/login" method="post">
        <p class="fs-6">    </p>

        <div class="mb-3">
          <label for="user" class="form-label"> <%=username%> </label>
          <input name="username" type="text" class="form-control" id="user" required pattern="\w*">

          <c:if test="${usernameStatus != null}">
            <div class="alert alert-danger" role="alert">
                ${usernameStatus}
            </div>
          </c:if>
        </div>

        <div class="mb-3">
          <label for="exampleInputPassword1" class="form-label"> <%=password%> </label>
          <input name="password" type="password" class="form-control" id="exampleInputPassword1">

          <c:if test="${passwordStatus != null}">
            <div class="alert alert-danger" role="alert">
                ${passwordStatus}
            </div>
          </c:if>
        </div>

        <div class="d-grid gap-2 col-6 mx-auto">
          <button class="btn btn-primary" type="submit">Log In</button>
        </div>

        <p class="fs-6">    </p>

        <p class="text-body-secondary">
          Don`t have an account? <a href="/register">Sign up</a>
        </p>
      </form>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

</body>
</html>