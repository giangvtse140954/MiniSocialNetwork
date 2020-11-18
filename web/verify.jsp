<%-- 
    Document   : verify
    Created on : Sep 18, 2020, 4:46:32 PM
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

            <!--welcome here-->
            <h4>${fullname}</h4>

            <h1>Verify your account</h1>
            <div class="container-fluid bg">
                <div class="row justify-content-center">
                    <div class="col-12 col-sm-6 col-md-3">
                        <form action="MainController" class="form-container">
                            <h2 class="text-center">Verify</h2>
                            <div class="form-group">
                                <label>Verification code</label>
                                <input type="text" name="txtCode" class="form-control" placeholder="Code" value="" />
                                <c:if test="${not empty param.error}">
                                    <small>Code is wrong</small>
                                </c:if>
                            </div>
                            <input type="submit" value="Confirm" name="btAction" class="btn btn-outline-primary" /><br/>
                            <c:url var="urlSearch" value="MainController">
                                <c:param name="btAction" value="Search"/>
                            </c:url>
                            <a href="${urlSearch}">Later</a>
                        </form>
                    </div>
                </div>
            </div>
        </c:if>
    </body>
</html>
