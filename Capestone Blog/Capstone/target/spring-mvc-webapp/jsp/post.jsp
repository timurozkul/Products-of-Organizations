<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>All Posts</title>

        <link href="${pageContext.request.contextPath}/css/post.css" rel="stylesheet">
        <!--        <link href="css/starter-template.css" rel="stylesheet">-->
        <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
        <script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

    </head>
    <body>
        <div class="login-container">

            <div id="backgroundImage" style="background-image: url(img/coffee-beans.jpg);">
                <div class="row">

                    <div id="navbar" class="col-lg-offset-2 col-lg-8 col-lg-offset-2">
                        <ul>
                            <li><a href="${pageContext.request.contextPath}/welcome">Welcome</a></li>
                            <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                            <li><a href="${pageContext.request.contextPath}/about">About</a></li>
                            <li><a href="${pageContext.request.contextPath}/shop">Shop</a></li>
                            <li role="presentation">
                                <a href="${pageContext.request.contextPath}/j_spring_security_logout">Log Out</a></li>
                        </ul><!-- NAVBAR-->
                    </div>
                    <!--//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////-->
                </div> 


                <script type="text/javascript" src="//cdn.tinymce.com/4/tinymce.min.js"></script>
                <script type="text/javascript">

                    tinymce.init({
                        selector: "textarea",
                        plugins: [
                            "advlist autolink lists link image charmap print preview anchor",
                            "searchreplace visualblocks code fullscreen",
                            "insertdatetime media table contextmenu paste",
                            "save", "textcolor", "wordcount", "emoticons"
                        ],
                        toolbar: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | \n\
                                  bullist numlist outdent indent | link image | save | forecolor backcolor | sizeselect | fontselect | fontsizeselect | emoticons ",
                        style_formats: [
                            {title: 'Open Sans', inline: 'span', styles: {'font-family': 'Open Sans'}},
                            {title: 'Arial', inline: 'span', styles: {'font-family': 'arial'}},
                            {title: 'Book Antiqua', inline: 'span', styles: {'font-family': 'book antiqua'}},
                            {title: 'Comic Sans MS', inline: 'span', styles: {'font-family': 'comic sans ms,sans-serif'}},
                            {title: 'Courier New', inline: 'span', styles: {'font-family': 'courier new,courier'}},
                            {title: 'Georgia', inline: 'span', styles: {'font-family': 'georgia,palatino'}},
                            {title: 'Helvetica', inline: 'span', styles: {'font-family': 'helvetica'}},
                            {title: 'Impact', inline: 'span', styles: {'font-family': 'impact,chicago'}},
                            {title: 'Symbol', inline: 'span', styles: {'font-family': 'symbol'}},
                            {title: 'Tahoma', inline: 'span', styles: {'font-family': 'tahoma'}},
                            {title: 'Terminal', inline: 'span', styles: {'font-family': 'terminal,monaco'}},
                            {title: 'Times New Roman', inline: 'span', styles: {'font-family': 'times new roman,times'}},
                            {title: 'Verdana', inline: 'span', styles: {'font-family': 'Verdana'}}
                        ]

                    });
                </script>

                <!--  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////              -->
                <div class="row">
                    <div class="context_down col-lg-offset-2 col-lg-8 col-lg-offset-2">

                        <form method="POST" class="form-horizontal">
                            <div class="form-group">
                                <input type="text" class="form-control" id="author" placeholder="Author" style="width:14em;"/>
                                <textarea name="cont1" id="cont1" style="width:100%"></textarea>
                            </div>
                            <div class="form-group">
                                <input  id="tagField" style="width:14em;" type="text" placeholder="">
                                <button id="tagsButton" type="button">Add Tags</button>

                            </div>
                            <div class="form-group">
                                <input class="btn btn-primary" id="submit-button" type="submit" value="Submit" style="width:8em;">

                            </div>
                        </form>
                    </div>
                </div>
            </div><!-- container-->
        </div>

        <!--////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////-->



        <script src="${pageContext.request.contextPath}/js/tinyMce.js"></script>
    </body>
</html>
