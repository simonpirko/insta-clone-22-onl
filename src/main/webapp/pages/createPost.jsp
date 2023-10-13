<%--
  Created by IntelliJ IDEA.
  User: ilyamoiseenko
  Date: 23.09.23
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Create post</title>
</head>
<body>
<%@include file="_header.jsp"%>

<div class="row justify-content-center">
  <div class="col-4">
    <div class="container">
      <form action="/create-post" method="post" enctype="multipart/form-data">
        <p class="fs-6">    </p>

        <h4>
          <div class="text-center mb-3" style="width: calc(100% - 0px);">Create new post</div>
        </h4>

        <div class="input-group flex-nowrap mb-3">
          <input name="photo" type="file" class="form-control" placeholder="link" aria-label="link" aria-describedby="addon-wrapping" required>
        </div>

        <div class="form-floating">
          <textarea name="description" class="form-control" placeholder="Leave a description here" id="floatingTextarea" maxlength="2200" required></textarea>
          <label for="floatingTextarea">Description</label>
        </div>

        <div class="form-floating">
          <textarea name="tag" class="form-control" placeholder="#car #test #nature" id="tag" maxlength="2200" required></textarea>
          <label for="tag">Tag</label>
        </div>

        <p class="fs-6">    </p>

        <div class="d-grid gap-2 col-6 mx-auto">
          <button class="btn btn-primary" type="submit">Create</button>
        </div>
      </form>
    </div>
  </div>
</div>
</body>
</html>
