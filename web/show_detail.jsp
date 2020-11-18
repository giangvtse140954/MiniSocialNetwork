<%-- 
    Document   : show_detail
    Created on : Sep 26, 2020, 12:12:07 PM
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
        <c:set var="status" value="${sessionScope.STATUS}"/>
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
            <!--end of navbar-->


            <!--welcome here-->
            <h4>${fullname}</h4>

            <!--detail article-->
            <c:set var="dto" value="${requestScope.DETAIL}"/> 
            <c:set var="postUser" value="${requestScope.POSTUSER}"/>
            <div class="card-container">
                <div class="card">
                    <div class="card-header" style="padding: 10px 0px 5px 20px">
                        <font color="#057BFF">
                        <h5>${postUser}</h5>
                        </font>
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">${dto.title}</h5>
                        <p class="card-text">${dto.description}</p>
                        <p class="card-text"><small class="text-muted">${dto.date}</small></p>
                    </div>
                    <img src="images/${dto.image}" class="card-img-bottom" alt="Image here">
                    <hr/>

                    <!--bar to show status emotion-->
                    <c:set var="cmt" value="${requestScope.CMT}"/>
                    <c:set var="like" value="${requestScope.LIKE}"/>
                    <c:set var="dislike" value="${requestScope.DISLIKE}"/>
                    <c:set var="userlike" value="${requestScope.USERLIKE}"/>
                    <c:set var="userdislike" value="${requestScope.USERDISLIKE}"/>
                    <c:url var="urlLike" value="MainController">
                        <c:param name="btAction" value="like"/>
                        <c:param name="txtPostId" value="${dto.postId}"/>
                    </c:url>
                    <c:url var="urlDislike" value="MainController">
                        <c:param name="btAction" value="dislike"/>
                        <c:param name="txtPostId" value="${dto.postId}"/>
                    </c:url>

                    <ul class="statusbar">
                        <li class="item ${userlike}"><span class="badge badge-light badge-pill">${like}</span><a href="${urlLike}">Like</a></li>
                        <li class="item ${userdislike}"><span class="badge badge-light badge-pill">${dislike}</span><a href="${urlDislike}">Dislike</a></li>
                        <li class="item"><span class="badge badge-light badge-pill">${cmt}</span><a href="#comment">Comment</a></li>
                    </ul>
                    <hr/>

                    <!--begin comment part-->
                    <c:set var="email" value="${sessionScope.EMAIL}"/>
                    <c:set var="cmtList" value="${requestScope.CMTLIST}"/>
                    <c:set var="names" value="${requestScope.NAMES}"/>
                    <c:forEach var="cmtItem" items="${cmtList}" varStatus="counter">
                        <form action="MainController" method="post">
                            <div class="card border-primary" style="margin: 0px 20px 10px 20px">
                                <div class="card-body" style="padding: 10px 0px 12px 20px;">
                                    <h6 class="card-title">     
                                        <c:if test="${email == cmtItem.email}">
                                            <input type="submit" name="btAction" value="x" 
                                                   onclick="return confirm('Are you sure you want to delete?')" 
                                                   class="btn btn-outline-primary"/>
                                            <input type="hidden" name="txtCmtId" value="${cmtItem.cmtId}" />
                                            <input type="hidden" name="txtPostId" value="${dto.postId}" />
                                            <input type="hidden" name="txtNotifyId" value="${cmtItem.notifyId}" />
                                        </c:if>
                                        <b>${names[counter.count - 1]}</b>
                                        <small>${cmtItem.date}</small></h6>
                                    <p class="card-text">${cmtItem.content}</p>
                                </div>
                            </div>       
                        </form>



                        <!--end of modal-->
                    </c:forEach>




                    <form action="MainController" method="post">
                        <div class="form-group" style="margin: 0px 20px 20px 20px" id="comment">
                            <input type="text" name="txtContent" class="form-control" placeholder="Write a comment..." value="" required maxlength="500" />
                        </div>
                        <input type="hidden" name="btAction" value="Post Comment" />
                        <input type="hidden" name="txtPostId" value="${dto.postId}" />
                    </form>
                    <!--end comment part-->
                </div>
            </div>
            <!--end detail article-->
        </c:if>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    </body>
</html>
