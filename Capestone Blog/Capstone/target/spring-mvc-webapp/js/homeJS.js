

$(document).ready(function () {
    
    $.ajax({
        type: 'GET',
        url: 'posts'
    }).success(function (data, status) {

        displayPosts(data, status);
        displayComments(data, status);
        ajaxAddComment();
        toggelComment();
        getPostsByTag();
        
    });
});

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

function displayPosts(posts, status) {

    $.each(posts.reverse(), function (index, post) {
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



function toggelComment() {

    $(".commentTitle").click(function () {
        var id = $(this).attr('id');
        $("#commentSection" + id).toggle("slow");
    });
}

function ajaxAddComment() {

    $('.commentButton1').click(function (event) {

        var idUnCut = $(this).attr('id');
        var idValue = idUnCut.replace("comment", "");
      
        $.ajax({
            type: 'POST',
            url: 'comment',
            data: JSON.stringify({
                postId: idValue,
                author: $('#CommentAuthor' + idValue).val(),
                content: $('#putComment' + idValue).val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status) {
            alert("Comment 44added");
            onlyGetComments();
        });
    });
}

function onlyGetComments() {
    $.ajax({
        type: 'GET',
        url: 'posts'
    }).success(function (data, status) {
        displayComments(data, status);
    });
}

function getPostsByTag(){    //Enter refreshes the page. The button loads content
 
    // CLICK ACTION
     $('#tagButton').click(function getTags(event) {
        var tagWord = $('#tagSearch').val();
      $.ajax({
            type: 'GET',
            url: 'postsByTag/' + tagWord
            }).success(function (data, status) {           
             $('#parentPostBox').empty();
             displayPosts(data, status);
        }).error(function (data, status) {
             alert("tag not found");
        });
    });
    
         // ENTER KEY
    $('#tagSearch').keypress(function(e){
        if(e.which === 13){
             e.preventDefault();
             $('#tagButton').click();
}   
    });
    }

//function sendId(){
//    
//     $('.postBox').click(function (event) {
//         var id = $(this);
//     
//        $.ajax({
//            type: 'POST',
//            url: 'tansferId/' + id            
//        }).success(function (data, status) {
//             //redirect to editpage
//        });
//});
//}