<%-- 
    Document   : post_article
    Created on : Sep 26, 2020, 1:55:17 PM
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
                            <a class="nav-link" href="#">Post Article</a>
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
        </c:if>
        <!--end of navbar-->


        <!--welcome here-->
        <h4>${fullname}</h4>

        <!--begin of form to post article-->
        <div class="container-fluid bg">
            <div class="row justify-content-center">
                <div class="col-12 col-sm-6 col-md-3">
                    <form action="MainController" method="POST" class="form-container" enctype="multipart/form-data">
                        <h2 class="text-center">Post Article</h2>
                        <div class="form-group">
                            <label>Title</label>
                            <input type="text" class="form-control" placeholder="Title" name="txtTitle" value="" required maxlength="50">
                        </div>
                        <div class="form-group">
                            <label>Description</label>
                            <input type="text" class="form-control" placeholder="Description" name="txtDescription" required maxlength="500">
                        </div>
                        <div class="form-group">
                            <label for="exampleFormControlFile1">File input</label>
                            <input type="file" class="form-control-file" id="exampleFormControlFile1" name="fileUp" accept=".jpg,.gif,.png" required>
                        </div>
                        <input type="submit" value="Post" name="btAction" class="btn btn-outline-primary"/>
                    </form>
                </div>
            </div>
        </div>
        <!--end of form to post article-->



        <!--import bootstrap here-->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>

    </body>
</html>
