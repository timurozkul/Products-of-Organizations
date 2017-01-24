<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Coffee Shop</title>
        <link href="${pageContext.request.contextPath}/css/home.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    </head>
    <body> <div class="container-fluid">
            <div id="backgroundImage" style="background-image: url(img/coffee-beans.jpg);">



                <div class="row">                
                    <div id="logo" class="col-lg-8 col-lg-offset-2"><img src="img/logo_white.png" style="width:40%;" alt=""></div>                               
                    <a href="logIn.jsp"><span style="font-size:2.5em; color: white; text-align: right;"  class="glyphicon glyphicon-pencil col-lg-1 col-lg-offset-1" aria-hidden="true"></span></a>        
                </div>  <!-- 1row -->
                <div class="row">
                    <div id="navbar" class="col-lg-offset-2 col-lg-8">
                        <ul>
                            <li><a href="${pageContext.request.contextPath}/welcome">Welcome</a></li>
                            <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                            <li><a href="${pageContext.request.contextPath}/about">About</a></li>
                            <li><a href="${pageContext.request.contextPath}/shop">Shop</a></li>
                        </ul><!-- NAVBAR-->
                    </div>

                </div>
                <!-- row -->
                <!-- //////////////////////////////////////////////////////////////////////////////////-->
                <div class="row" >
                    <div  id="parentPostBox" class=" col-lg-offset-2 col-lg-8  ">                     
                    </div>

                    <div id="searchBox" class="col-lg-1">
                        <form class="form-inline" role="form">
                            <div class="form-group">                          
                                <input type="text" class="form-control" id="tagSearch" placeholder="Search Tag">
                                <button type="submit" class="btn btn-default">></button>
                            </div>                       
                        </form>
                    </div><!-- /input-group -->

                </div>
                <div class="row">
                    <div id="footer"></div>
                </div>
            </div>
        </div>

        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/homeJS.js"></script>
    </body>
</html>
