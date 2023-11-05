<%@ page import="java.util.Properties" %><%--
  Created by IntelliJ IDEA.
  User: Nastya
  Date: 11/1/2023
  Time: 2:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Properties properties = (Properties) request.getAttribute("properties");
    String text = properties.getProperty("createStory.text");
    String description = properties.getProperty("createStory.description");
    String button = properties.getProperty("createStory.button");
%>
<html>
<head>
    <title>Create story</title>
</head>
<body>
<jsp:include page="_header.jsp"/>

<div class="row justify-content-center">
    <div class="col-4">
<div class="container">

    <form action="/user/create_story" method="post" enctype="multipart/form-data">

        <h4>
            <div class="text-center mb-3" style="width: calc(100% - 0px);"><%=text%></div>
        </h4>

        <div class="input-group flex-nowrap mb-3">
            <input name="add story" type="file" class="form-control" placeholder="link" aria-label="link" aria-describedby="addon-wrapping" required>
        </div>

        <div class="form-floating">
            <textarea name="description" class="form-control" placeholder="Leave a description here" id="floatingTextarea" maxlength="1100" required></textarea>
            <label for="floatingTextarea"><%=description%></label>
        </div>


        <div class="form-floating">
            <textarea name="hashtag" class="form-control" placeholder="#car #test #nature" id="tag" maxlength="1100" required></textarea>
            <label for="tag">Tag</label>
        </div>


            <form>
            <div class="form-group">
                <label for="exampleFormControlFile1">Example file input</label>
                <input type="file" class="form-control-file" id="exampleFormControlFile1">
            </div>
        </form>

        <div class="d-grid gap-2 col-6 mx-auto">
            <button class="btn btn-primary" type="submit"><%=button%></button>
        </div>
    </form>

</div>
    </div>
</div>

</body>
</html>
