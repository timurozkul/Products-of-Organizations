/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var tagsArray= [];
 
$(document).ready(function () {

    $.ajax({
        type: 'GET',
        url: 'posts'
    }).success(function (data, status) {

        displayPosts(data, status);
        displayComments(data, status);
        ajaxAddComment();
        toggelComment();
        deleteComment();
    });
       
       tagCreator();
    $('#submit-button').click(function (event) {
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: 'post',//need change !!!
            data: JSON.stringify({
                author: $('#author').val(),
                content: tinyMCE.get('content').getContent(),
                tags: tagsArray
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status) {
        alert("Successfuly uploaded to the Server");    
                
        });
    });
      });

function tagCreator(){
    $('#tagsButton').click(function (event) {
        var tags1 = $('#tagField').val();
        var tags = [];
     
         $('#tagField').val('');               
        tags = tags1.split(" ");    
        
        for(var i = 0; i < tags.length; i++){
            var tag = {name:tags[i]};          
            tagsArray.push(tag);
           
        }
            });
}

function displayPosts(posts, status) {

    $.each(posts, function (index, post) {
        var tags = post.tags;
        var textTags = "";

        ///////////////////// TAGS ////////////////////////////////
        for (var i = 0; i < tags.length; i++) {
            textTags = textTags + " #" + tags[i]["name"];
        }
        ///////////////////// GENERAL ////////////////////////////////    
        $('#parentPostBox').append($('<div class="postBox" id="' + post.postId + '">'
                + post.content //POST CONTENT
                + '<p class="tags"> ' + textTags //TAGS
                + '</p><h5 class="commentTitle" id="' + post.postId //COMMENTS TITLE
                + '">Comments<img src="img/arrow486.png" alt="" style="width:1em;"></h5><div id="commentSection' + post.postId //COMMENTS
                + '" class="commentStyle"></div><form id="form"><input type="text" class="inputAuthor" placeholder="Author" id="CommentAuthor'//COMMENT AUTHOR - same author same post will be bug
                + post.postId + '"><input type="text" class="inputComment" placeholder="Comment" id="putComment'
                + post.postId + '"></form><button class="commentButton1" id="comment'
                + post.postId + '" type="submit">Add Comment</button>'));//COMMENT BUTTON
    });
}
function displayComments(posts, status) {

    $.each(posts, function (index, post) {
        var comments = post.comments;
        var textComment = "";
        for (var i = 0; i < comments.length; i++) {
            var textComment = "<p id='commentId"+ comments["commentId"] + "'><span class='commentAuthor'>" + comments[i]["author"] + ":</span> " + comments[i]["content"] + "<span class='commentDate'> " + comments[i]["date"] + "</span></p>";
            var divAdd = ("#commentSection" + post.postId);//ATTACHING TO POST
            $(divAdd).append(textComment);
        }
    });
}

function toggelComment() {

    $(".commentTitle").click(function () {
        var id = $(this).attr('id');
        $("#commentSection" + id).toggle("slow");
    });
}

function deletePost(){
        $('.postBox').click(function (event) {
        var id = $(this);
        
        $.ajax({
            type: 'POST',
            url: 'post/' + id            
        }).success(function (data, status) {
             displayPosts(data, status);
             displayComments(data, status);
        });
    });
}

function deleteComment(){
    $('.commentStyle').click(function (event) {
        var idUnCut = $(this).attr('id');
        var id = idUnCut.replace("commentId", "");
        
        $.ajax({
            type: 'POST',
            url: 'comment/' + id            
        }).success(function (data, status) {
             
        });
    });   
}
