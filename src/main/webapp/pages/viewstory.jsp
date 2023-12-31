<%@ page import="java.util.Properties" %><%--
  Created by IntelliJ IDEA.
  User: Nastya
  Date: 11/5/2023
  Time: 12:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Properties properties = (Properties) request.getAttribute("properties");
    String button = properties.getProperty("viewstory.button");
    String editstory = properties.getProperty("viewstory.editstory");
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Story ${story.getId()}</title>
</head>
<body>
<jsp:include page="_header.jsp"/>
<div class="container mt-3">
    <div class="text-end">
        <button onclick="history.back()" class="btn border-0">[x]<%=button%>
        </button>
    </div>
    <div class="card mb-3 rounded-0">
        <div class="row g-0" style="height: 800px; background-color: black">
            <div class="col-sm-7 align-self-center" style="text-align: center">
                <img src="data:image/jpg;base64,${story.getPhotoOrVideo()}" class="img-fluid" style="max-height: 800px"
                     alt="story image">
            </div>
            <div class="col-sm-5" style="background-color: white">
                <div class="card-header" style="height: 80px">
                    <div class="row g-0">
                        <div class="col-sm-6 text-start">
                            <div class="row g-0">
                                <a class="page-link col-sm-2 align-self-center text-center"
                                   href="/user/profile?username=${story.getUser().getUsername()}"
                                   style="padding: unset; text-decoration: unset">
                                    <img src="data:image/jpg;base64,${story.getUser().getPhoto()}"
                                         style="padding: unset; height: 24px; width: 24px" alt="profile image">
                                </a>
                                <a class="page-link col-sm-10 align-self-center text-center"
                                   href="/user/profile?username=${story.getUser().getUsername()}"
                                   style="padding: unset; text-decoration: unset">
                                    ${story.getUser().getUsername()}
                                </a>
                            </div>
                        </div>
                        <div class="col-sm-2 align-self-center text-center">
                            <c:if test="${user.getId() == story.getUser().getId()}">
                                <a class="page-link col-sm-10 align-self-center text-center"
                                   href="/user/editstory?id=${story.getId()}"
                                   style="padding: unset; text-decoration: unset">
                                    <%=editstory%>
                                </a>
                            </c:if>
                        </div>

                        <div class="col-sm-3 align-self-center text-end">
                            ${likes}
                        </div>
                        <c:if test="${like == false}">
                            <form action="/like_story" method="post" class="col-sm-1 align-self-center text-center">
                                <input type="hidden" name="story_id" value="${story.getId()}">
                                <button type="submit" class="btn border-0"
                                        style="padding: unset; --bs-btn-hover-color: red; transition: 0.3s">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor"
                                         class="bi bi-heart-fill object-fit-cover" viewBox="0 0 16 16">
                                        <path fill-rule="evenodd"
                                              d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"></path>
                                    </svg>
                                </button>
                            </form>
                        </c:if>
                        <c:if test="${like == true}">
                            <form action="/unlike_story" method="post" class="col-sm-1 align-self-center text-center">
                                <input type="hidden" name="story_id" value="${story.getId()}">
                                <button type="submit" class="btn border-0"
                                        style=" --bs-body-color: red; padding: unset; --bs-btn-hover-color: black; transition: 0.3s">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor"
                                         class="bi bi-heart-fill object-fit-cover" viewBox="0 0 16 16">
                                        <path fill-rule="evenodd"
                                              d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"></path>
                                    </svg>
                                </button>
                            </form>
                        </c:if>


                        <div class="col-sm-12 align-self-center text-end">
                            <div class="dropdown">
                                <button class="btn btn-warning btn-sm dropdown-toggle" type="button"
                                        id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                                    Reaction
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                    <li><a class="dropdown-item"
                                           href="/user/story/create_reaction?story_id=${story.getId()}">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                             fill="currentColor" class="bi bi-emoji-smile" viewBox="0 0 16 16">
                                            <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                            <path d="M4.285 9.567a.5.5 0 0 1 .683.183A3.498 3.498 0 0 0 8 11.5a3.498 3.498 0 0 0 3.032-1.75.5.5 0 1 1 .866.5A4.498 4.498 0 0 1 8 12.5a4.498 4.498 0 0 1-3.898-2.25.5.5 0 0 1 .183-.683zM7 6.5C7 7.328 6.552 8 6 8s-1-.672-1-1.5S5.448 5 6 5s1 .672 1 1.5zm4 0c0 .828-.448 1.5-1 1.5s-1-.672-1-1.5S9.448 5 10 5s1 .672 1 1.5z"/>
                                        </svg>
                                    </a></li>
                                    <li><a class="dropdown-item"
                                           href="/user/story/create_reaction?story_id=${story.getId()}">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                             fill="currentColor" class="bi bi-fire" viewBox="0 0 16 16">
                                            <path d="M8 16c3.314 0 6-2 6-5.5 0-1.5-.5-4-2.5-6 .25 1.5-1.25 2-1.25 2C11 4 9 .5 6 0c.357 2 .5 4-2 6-1.25 1-2 2.729-2 4.5C2 14 4.686 16 8 16Zm0-1c-1.657 0-3-1-3-2.75 0-.75.25-2 1.25-3C6.125 10 7 10.5 7 10.5c-.375-1.25.5-3.25 2-3.5-.179 1-.25 2 1 3 .625.5 1 1.364 1 2.25C11 14 9.657 15 8 15Z"/>
                                        </svg>
                                    </a></li>
                                    <li><a class="dropdown-item"
                                           href="/user/story/create_reaction?story_id=${story.getId()}">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                             fill="currentColor" class="bi bi-emoji-grimace" viewBox="0 0 16 16">
                                            <path d="M7 6.25c0 .69-.448 1.25-1 1.25s-1-.56-1-1.25S5.448 5 6 5s1 .56 1 1.25Zm3 1.25c.552 0 1-.56 1-1.25S10.552 5 10 5s-1 .56-1 1.25.448 1.25 1 1.25Zm2.98 3.25A1.5 1.5 0 0 1 11.5 12h-7a1.5 1.5 0 0 1-1.48-1.747v-.003A1.5 1.5 0 0 1 4.5 9h7a1.5 1.5 0 0 1 1.48 1.747v.003Zm-8.48.75h.25v-.75H3.531a1 1 0 0 0 .969.75Zm7 0a1 1 0 0 0 .969-.75H11.25v.75h.25Zm.969-1.25a1 1 0 0 0-.969-.75h-.25v.75h1.219ZM4.5 9.5a1 1 0 0 0-.969.75H4.75V9.5H4.5Zm1.75 2v-.75h-1v.75h1Zm.5 0h1v-.75h-1v.75Zm1.5 0h1v-.75h-1v.75Zm1.5 0h1v-.75h-1v.75Zm1-2h-1v.75h1V9.5Zm-1.5 0h-1v.75h1V9.5Zm-1.5 0h-1v.75h1V9.5Zm-1.5 0h-1v.75h1V9.5Z"/>
                                            <path d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16Zm0-1A7 7 0 1 1 8 1a7 7 0 0 1 0 14Z"/>
                                        </svg>
                                    </a></li>
                                    <li><a class="dropdown-item"
                                           href="/user/story/create_reaction?story_id=${story.getId()}">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                             fill="currentColor" class="bi bi-emoji-surprise" viewBox="0 0 16 16">
                                            <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14Zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16Z"/>
                                            <path d="M7 5.5C7 6.328 6.552 7 6 7s-1-.672-1-1.5S5.448 4 6 4s1 .672 1 1.5Zm4 0c0 .828-.448 1.5-1 1.5s-1-.672-1-1.5S9.448 4 10 4s1 .672 1 1.5ZM10 11a2 2 0 1 1-4 0 2 2 0 0 1 4 0Z"/>
                                        </svg>
                                    </a></li>
                                </ul>
                            </div>
                        </div>


                    </div>
                </div>
                <div class="card-body" style="height: 650px">
                    <p class="card-text overflow-y-scroll" style="height: 200px">
                        ${story.getDescription()}
                    </p>

                    <p class="card-text overflow-y-scroll" style="height: 200px">
                        <c:forEach items="${story.getHashtags()}" var="item">
                            #${item.getName()}
                        </c:forEach>
                    </p>

                    <div class="container-sm-5 border-top overflow-y-scroll" style="height: 380px; border-color: black">
                        <ul>
                            <c:forEach items="${story.getComments()}" var="comment" end="4">
                                <li>
                                    <div class="container border-bottom mt-2">
                                        <div class="row">
                                            <div class="col-sm-8 align-self-center">
                                                <div class="row g-0">
                                                    <a class="page-link col-sm-2 align-self-center text-center"
                                                       href="/user/profile?username=${comment.getUser().getUsername()}"
                                                       style="padding: unset; text-decoration: unset">
                                                        <img class="img-fluid rounded-5"
                                                             src="data:image/jpg;base64,${comment.getUser().getPhoto()}"
                                                             style="padding: unset; height: 24px; width: 24px"
                                                             alt="profile image">
                                                    </a>

                                                    <a class="page-link col-sm-2 align-self-center text-center"
                                                       href="/user/profile?username=${comment.getUser().getUsername()}"
                                                       style="padding: unset; text-decoration: unset">${comment.getUser().getUsername()}</a>
                                                </div>
                                            </div>

                                            <div class="col-sm-4 text-end">
                                                <c:if test="${user.getId() == comment.getUser().getId()}">
                                                    <form action="/user/delete_story_comment" method="post"
                                                          class="col-sm-6 align-self-center text-center">
                                                        <input type="hidden" name="storyId" value="${story.getId()}">
                                                        <input type="hidden" name="commentId"
                                                               value="${comment.getId()}">
                                                        <button class="btn border-0" type="submit"
                                                                style="padding: unset">del
                                                        </button>
                                                    </form>
                                                </c:if>
                                            </div>
                                        </div>
                                        <small>
                                                ${comment.getText()}
                                        </small>
                                        <div>
                                            <small class="text-body-secondary align-self-end">
                                                    ${formattedCommentTime}
                                            </small>
                                        </div>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="card-footer" style="height: 70px">
                    <form action="/user/create_story_comment" class="row g-2" method="post">
                        <div class="col-sm-11 align-self-center">
                            <input name="commentMessage" type="text" class="form-control" placeholder="create comment"
                                   aria-label="create comment" style="height: 50px;">
                            <input type="hidden" name="story_id" value="${story.getId()}">
                        </div>
                        <div class="col-1 align-self-center">
                            <button type="submit" class="btn border-0"
                                    style="padding: unset; --bs-btn-hover-color: blue; transition: 0.3s">
                                <svg xmlns="http://www.w3.org/2000/svg" width="45" height="45" fill="currentColor"
                                     class="bi bi-arrow-up-right-square-fill" viewBox="0 0 16 16" size="cover">
                                    <path d="M14 0a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2h12zM5.904 10.803 10 6.707v2.768a.5.5 0 0 0 1 0V5.5a.5.5 0 0 0-.5-.5H6.525a.5.5 0 1 0 0 1h2.768l-4.096 4.096a.5.5 0 0 0 .707.707z"></path>
                                </svg>
                            </button>
                        </div>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>

</body>
</html>