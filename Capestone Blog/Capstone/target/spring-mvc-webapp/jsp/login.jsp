<%-- 
    Document   : login
    Created on : Dec 5, 2015, 11:03:13 AM
    Author     : apprentice
--%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Coffee Shop</title>

        <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
    </head>
    <body> <div id="backgroundImage" style="background-image: url(img/2257.jpg);">
            <div class="container-fluid">
                <row>
                    <div class="row">
                        <div id="navbar" class="col-lg-offset-2 col-lg-8">
                            <ul>
                                <li><a href="${pageContext.request.contextPath}/welcome">Welcome</a></li>
                                <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                                <li><a href="${pageContext.request.contextPath}/about">About</a></li>
                                <li><a href="${pageContext.request.contextPath}/shop">Shop</a></li>
                            </ul><!-- NAVBAR-->
                        </div>
                </row>
                <row>
                    <div class="col-lg-offset-2 col-lg-2">
                        <c:if test="${param.login_error == 1}">
                            <h3 style="color: #FFF"><strong>Invalid username or password</strong></h3>
                        </c:if>
                        <!--                        <form class="form-horizontal">
                                                    <div class="form-group">
                                                        <input type="email" class="form-control" id="inputEmail" placeholder="Username">
                                                    </div>
                                                    <div class="form-group">
                                                        <input type="password" class="form-control" id="inputPassword" placeholder="Password">
                                                    </div>
                                                    <div class="form-group">
                        
                                                        <button type="submit" class="btn btn-default">Sign in</button>
                        
                                                    </div>
                                                </form>-->

                        <form method="post" class="signin"
                              action="j_spring_security_check">
                            <fieldset>
                                <table cellspacing="0">
                                    <tr>
                                        <th>
                                            <label for="username">Username
                                            </label>
                                        </th>

                                        <td><input id="username_or_email"
                                                   name="j_username"
                                                   type="text" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><label for="password">Password</label></th>
                                        <!-- #2b - must be j_password for Spring -->
                                        <td><input id="password"
                                                   name="j_password"
                                                   type="password" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <th></th>
                                        <td><input name="commit" type="submit"
                                                   value="Sign In" /></td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
                    </div>
                    <div class="col-lg-offset-3 col-lg-5">                                 
                        <div id="logo" class="col-md-8 col-md-offset-2"><img src="img/logo_white.png" style="width:25em; padding-top: 5em;" alt="Mountain View"></div>                                                      
                    </div>
                </row>
            </div>
        </div>
    </div>

</body>
</html>
