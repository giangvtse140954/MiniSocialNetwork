<%-- 
    Document   : search
    Created on : Sep 16, 2020, 11:27:55 AM
    Author     : MY HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MiniSN</title>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <link rel="stylesheet" href="css/style.css" />
    </head>
    <body>
        <c:set var="fullname" value="${sessionScope.FULL_NAME}"/>
        <c:set var="email" value="${sessionScope.EMAIL}"/>
        <c:set var="status" value="${sessionScope.STATUS}"/>
        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <c:if test="${empty fullname}">
            <c:url var="urlLoginPage" value="MainController">
                <c:param name="btAction" value="login_page"/>
            </c:url>
            <c:redirect url="${urlLoginPage}"/>
        </c:if>
        <c:if test="${not empty fullname}">
            <c:url var="home" value="MainController">
                <c:param name="btAction" value="Search"/>
            </c:url>
            <c:url var="urlPostPage" value="MainController">
                <c:param name="btAction" value="post_page"/>
            </c:url>
            <c:url var="noti" value="MainController">
                <c:param name="btAction" value="Noti"/>
            </c:url>

            <!--navbar here-->
            <nav class="navbar navbar-expand-lg navbar-light fixed-top">
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <a class="navbar-brand" href="${home}">MiniSN</a>

                <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
                    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                        <li class="nav-item active">
                            <a class="nav-link" href="${home}">Home <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="${urlPostPage}">Post Article</a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="${noti}">Notification</a>
                        </li>

                        <!--verify here-->
                        <c:if test="${not empty status}">
                            <c:url var="urlVerifyPage" value="MainController">
                                <c:param name="btAction" value="verify_page"/>
                            </c:url>
                            <li class="nav-item active">
                                <a class="nav-link" href="${urlVerifyPage}">Verify</a>
                            </li>
                        </c:if>

                        <!--logout here-->
                        <c:url var="urlLogout" value="MainController">
                            <c:param name="btAction" value="Logout"/>
                        </c:url>
                        <li class="nav-item active">
                            <a class="nav-link" href="${urlLogout}">Log out</a>
                        </li>
                    </ul>

                    <form class="form-inline my-2 my-lg-0" action="MainController">
                        <input class="form-control mr-sm-2" type="search" placeholder="Search Article" aria-label="Search"
                               name="txtSearchValue" value="${searchValue}" required>
                        <input type="submit" class="btn btn-outline-primary my-2 my-sm-0" value="Search" name="btAction" />
                    </form>
                </div>
            </nav>

            <!--welcome here-->
            <h4>${fullname}</h4>

            <!--when there is no result matched-->
            <c:set var="list" value="${requestScope.LIST}"/>
            <c:if test="${empty list}">
                <c:if test="${empty list}">
                    <div id="noResult">
                        <img src="https://www.iconfinder.com/data/icons/facebook-and-social-media-1/64/Facebook_and_Social_Media-17-512.png"/>
                        <h3>We didn't find any results</h3>
                        <h5>Make sure everything is spelled correctly or try different keywords.</h5>
                    </div>
                </c:if>
            </c:if>

            <!--list article-->
            <c:if test="${not empty list}">
                <div class="card-container">
                    <c:forEach var="dto" items="${list}">
                        <div class="card">
                            <form action="MainController" method="POST">
                                <div class="card-body">
                                    <h5 class="card-title">
                                        ${dto.title}
                                        <c:if test="${email == dto.email}">
                                            <input type="submit" name="btAction" value="X" 
                                                   onclick="return confirm('Are you sure you want to delete?')" 
                                                   class="btn btn-outline-primary"/>
                                            <input type="hidden" name="txtPostId" value="${dto.postId}" />
                                            <input type="hidden" name="txtSearchValue" value="${searchValue}" />
                                        </c:if>

                                    </h5>
                                    <p class="card-text">${dto.description}</p>
                                    <p class="card-text"><small class="text-muted">${dto.date}</small></p>
                                </div>
                            </form>
                            <img src="images/${dto.image}" class="card-img-bottom" alt="Image here">
                            <form action="MainController">
                                <input type="submit" value="Detail" name="btAction" class="btn btn-link" />
                                <input type="hidden" name="txtPostId" value="${dto.postId}" />
                            </form>
                        </div>

                    </c:forEach>


                    <!--paging here-->
                    <c:set var="page" value="${requestScope.PAGE}"/>
                    <c:url var="urlNext" value="MainController">
                        <c:param name="btAction" value="ChangeNextPage"/>
                        <c:param name="txtSearchValue" value="${searchValue}"/>
                        <c:param name="page" value="${page}"/>
                    </c:url>
                    <c:url var="urlPre" value="MainController">
                        <c:param name="btAction" value="ChangePrePage"/>
                        <c:param name="txtSearchValue" value="${searchValue}"/>
                        <c:param name="page" value="${page}"/>
                    </c:url>

                    <!--paging-->
                    <nav aria-label="Page navigation example">
                        <ul class="pagination">
                            <li class="page-item"><a class="page-link" href="${urlPre}">Previous</a></li>
                            <li class="page-item"><a class="page-link" href="${urlNext}">Next</a></li>
                        </ul>
                    </nav>
                </div>
            </c:if>
        </c:if>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    </body>
</html>
