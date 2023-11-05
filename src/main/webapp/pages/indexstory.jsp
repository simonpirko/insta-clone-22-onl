<%@ page import="java.util.Properties" %><%--
  Created by IntelliJ IDEA.
  User: Nastya
  Date: 11/5/2023
  Time: 4:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%

    Properties properties = (Properties) request.getAttribute("properties");
    String next = properties.getProperty("index.next");
    String previous = properties.getProperty("index.previous");
    String button = properties.getProperty("index.button");
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul>
    <div class="row row-cols-auto justify-content-md-center" style="margin-left: 50px">
        <c:forEach items="${storyPage.getItemsForPageList()}" var="item">

            <div class="col">
                <div class="row row-cols-auto justify-content-md-center">
                <a href="/user/view_story?id=${item.getId()}">
                    <img src="data:image/jpg;base64,${item.getUser().getPhoto()}" width="40px" height="40px"/>
                </a>
            </div>

                <div class="row row-cols-auto justify-content-md-center">
<%--                <p style="font-size: x-small">--%>
                    <a style="font-size: x-small" href="/user/profile?username=${item.getUser().getUsername()}">${item.getUser().getUsername()}</a>
<%--                </p>--%>
                </div>
            </div>

        </c:forEach>
    </div>
</ul>


<div class="container">
    <div class="row justify-content-center">
        <div class="col-4 mt-4 justify-content-center">
            <nav aria-label="...">
                <ul class="pagination">

                    <li class="page-item ${storyPage.hasPreviousPage() ? "" : "disabled"}">
                        <a class="page-link" href="/page?storyPage=${storyPage.getPreviousPage()}"
                           tabindex="-1"><%=previous%>
                        </a>
                    </li>

                    <c:forEach begin="${storyPage.getMinRange()}" end="${storyPage.getMaxRange()}" var="j">
                        <c:choose>
                            <c:when test="${storyPage.getPageNumber() eq j}">

                                <li class="page-item active" aria-current="page">
                                    <a class="page-link" href="/page?storyPage=${j}">${j}</a>
                                </li>

                            </c:when>

                            <c:otherwise>
                                <li class="page-item"><a class="page-link" href="/page?storyPage=${j}">${j}</a>
                                </li>

                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <li class="page-item ${storyPage.hasNextPage() ? "" : "disabled"}">
                        <a class="page-link" href="/page?storyPage=${storyPage.getNextPage()}"><%=next%>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</div>