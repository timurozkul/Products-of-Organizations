<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>About</title>
        <link href="${pageContext.request.contextPath}/css/aboutCSS.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js"></script>
    </head>
    <body >

        <div class="container-fluid">
            <div id="titleImage" style="background-image: url(img/coffeee.jpg);">

                <div class="row">                
                    <div id="logo" class="col-md-8 col-md-offset-2"><img src="img/logo_white.png" style="width:40%;" alt="Mountain View"></div>                               
                    <span style="font-size:2.5em; color: white; text-align: right;"  class="glyphicon glyphicon-pencil col-md-1 col-md-offset-1" aria-hidden="true"></span>        
                </div>  <!-- 1row -->
                <div class="row">
                    <div id="navbar" class="col-md-12">
                        <ul>
                            <li><a href="${pageContext.request.contextPath}/welcome">Welcome</a></li>
                            <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                            <li><a href="${pageContext.request.contextPath}/about">About</a></li>
                            <li><a href="${pageContext.request.contextPath}/shop">Shop</a></li>
                        </ul><!-- NAVBAR-->
                    </div><!-- row -->
                </div> 

            </div>
            <!--  //////////////////////////////////////////////////////////////////////////////////-->
            <div id="block2" class="row" style="background-color: white;">
                <div class="col-md-offset-2 col-md-8 col-md-offset-2">
                    <h1 style="color: #FAE9C4; font-family: comicF; text-align: center; font-size: 4em; text-shadow: 1px 1px #3C3635;">Discover Our</h1>
                    <h2 style="color:  #3C3635; font-family: impactA; text-align: center; padding-top: -2em;">Distinct Style</h2>
               <p style="text-align: center; font-family: normal; color: black;">rem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p></div></div>  
            </div>
            <div class="row">
                <div id="about" style="background-image: url(img/coffee-beans.jpg);"><div><h1 style="font-family: comicF; font-size: 2em; color: #FAE9C4;">Lorem totem</h1>
                        <p>rem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p></div></div>  
            </div>
            <div class="row">
                <div id="team">
                    <h1 id="content" style="font-family: impactA; font-size: 4em; color: #4C3F37;">Our amazing team</h1><hr id="line1"/>
                    <div class="col-md-4 teamDiv" ><img src="img/woman3.jpg" class="img-circle" alt="" style="width:24%;">
                        <p style="font-size: 3em; padding-top: 1em; font-family: comicC; margin-bottom: 0em;">Lucy Luu</p ><p style="color: #4b433b;">Marketing Director</p>
                        <div class="socialIcons"><img src="img/facebook3.png" alt="" style="width:2em;">
                            <img src="img/twitter3.png" alt="" style="width:2em;">
                            <img src="img/linkedin3.png" alt="" style="width:2em;">
                        </div>
                    </div>
                    <div class="col-md-4 teamDiv"><img src="img/woman4.jpg" class="img-circle" alt="" style="width:24%;">
                        <p style="font-size: 3em; padding-top: 1em; font-family: comicC; margin-bottom: 0px;">Melissa Gunson</p ><p style="color: #4b433b;">Founder</p>
                        <div class="socialIcons">     <img src="img/facebook3.png" alt="" style="width:2em;">
                            <img src="img/twitter3.png" alt="" style="width:2em;">
                            <img src="img/linkedin3.png" alt="" style="width:2em;">
                        </div>
                    </div>
                    <div class="col-md-4 teamDiv"><img src="img/man1.jpg" class="img-circle" alt="" style="width:24%;">
                        <p style="font-size: 3em;  padding-top: 1em; font-family: comicC; margin-bottom: 0px;">James Hutson</p><p style="color: #4b433b;">Store Manager</p>
                        <div class="socialIcons"><img src="img/facebook3.png" alt="" style="width:2em;">
                            <img src="img/twitter3.png" alt="" style="width:2em;">
                            <img src="img/linkedin3.png" alt="" style="width:2em;">
                        </div>
                    </div>
                </div>
            </div><!-- row -->
            <div class="row" id="infoGeneral">
               
                    <div class="col-md-4 io">
                        <div><img src="img/Bhome.png" alt="" style="width:7%; text-align: center;"></div>
                        <h1 style="color: white; font-family: comicF; font-size: 3em; margin-bottom: 0px;">Location</h1>
                        <p>252 E Market St</p>
                        <p>Louisville, KY 40202</p>
                        <div class="underScoreInfoGeneral"></div>
                    </div>
                    <div class="col-md-4 io">
                        <div><img src="img/Bdata.png" alt="" style="width:7%; text-align: center;"></div>         
                        <h2 style="color: white; font-family: comicF; font-size: 3em; margin-bottom: 0px;">Opening Times</h2>
                        <p>Monday - Saturday: 8:00 - 21:00</p>
                        <p>Sunday: closed</p>
                        <div class="underScoreInfoGeneral"></div>                
                    </div>
                    <div class="col-md-4 io">
                        <div><img src="img/Bworldwide.png" alt="" style="width:7%; text-align: center;"></div>         
                        <h2 style="color: white; font-family: comicF; font-size: 3em; margin-bottom: 0px;">Contact</h2>
                        <p><img src="img/letter106.png" alt="" style="width:1em; padding-right: 4px;"> jameshutson@gmail.com</p>
                        <p><img src="img/telephone5.png" alt="" style="width:1em; padding-right: 4px;"> 502 936 7664</p>
                        <div class="underScoreInfoGeneral"></div>                
                    </div>
                
            </div><!-- row -->
            <div class="row">
                <div class="col-md-12" id="map"></div>            
            </div>
            <div class="row">
                <div id="footer"></div>
            </div>
        </div><!-- container-->
        <script src="${pageContext.request.contextPath}/js/map.js"></script>   
        <script src="${pageContext.request.contextPath}/js/navbar.js"></script>          
    </body>
</html>