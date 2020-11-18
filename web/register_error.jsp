<%-- 
    Document   : register_error
    Created on : Sep 17, 2020, 7:30:19 PM
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
        <link rel="stylesheet" href="./css/style.css"/>
    </head>
    <body>
        <c:set var="errors" value="${requestScope.REGISTER_ERRORS}"/>
        <c:set var="fullname" value="${param.txtFullname}"/>
        <c:set var="email" value="${param.txtEmail}"/>
        <div class="container-fluid bg">
            <div class="row justify-content-center">
                <div class="col-12 col-sm-6 col-md-3">
                    <form class="form-container" action="MainController" method="POST">
                        <h2 class="text-center">Register</h2>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Email</label>
                            <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="txtEmail" value="${email}">
                            <c:if test="${not empty errors.emailLengthErr}">
                                <small>
                                    ${errors.emailLengthErr}
                                </small>
                            </c:if>
                        </div>
                        <div class="form-group">
                            <label for="formGroupExampleInput">Fullname</label>
                            <input type="text" class="form-control" id="formGroupExampleInput" name="txtFullname" value="${fullname}">
                            <c:if test="${not empty errors.fullnameLengthErr}">
                                <small>
                                    ${errors.fullnameLengthErr}
                                </small>
                            </c:if>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">Password</label>
                            <input type="password" class="form-control" id="exampleInputPassword1" name="txtPassword">
                            <c:if test="${not empty errors.passwordLengthErr}">
                                <small>
                                    ${errors.passwordLengthErr}
                                </small>
                            </c:if>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">Re-enter Password</label>
                            <input type="password" class="form-control" id="exampleInputPassword1" name="txtConfirm">
                            <c:if test="${not empty errors.confirmNotMatched}">
                                <small>
                                    ${errors.confirmNotMatched}
                                </small>
                            </c:if>
                        </div>
                        <input type="submit" value="Register" name="btAction" class="btn btn-outline-primary btn-block" />
                        <c:if test="${not empty errors.emailIsExisted}">
                            <font color="red">
                            <small>
                                ${errors.emailIsExisted}
                            </small><br/>
                            </font>
                        </c:if>
                        <c:url var="urlRewriting" value="MainController">
                            <c:param name="btAction" value="login_page"/>
                        </c:url>
                        <a href="${urlRewriting}">Login instead</a>
                    </form>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    </body>
</html>
