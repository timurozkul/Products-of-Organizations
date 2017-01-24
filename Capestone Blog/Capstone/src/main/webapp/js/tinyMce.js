/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// Document ready function

var tagsArray= [];

$(document).ready(function () {
       alert("Successfull"); 
       tagCreator();
    $('#submit-button').click(function (event) {
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: 'post',
            data: JSON.stringify({
                author: $('#author').val(),
                content: tinyMCE.get('cont1').getContent(),
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

