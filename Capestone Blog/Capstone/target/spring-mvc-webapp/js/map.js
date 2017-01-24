/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function initialize() {
    var mapCanvas = document.getElementById('map');
    var mapOptions = {
        scrollwheel: false,
        center: new google.maps.LatLng(38.254162, -85.748349),
        mapTypeControlOptions: {
      mapTypeIds: []
    },
        zoom: 13,
        mapTypeId: google.maps.MapTypeId.ROADMAP,
        styles: [{"featureType": "landscape", "stylers": [{"hue": "#FFBB00"}, {"saturation": 43.400000000000006}, {"lightness": 37.599999999999994}, {"gamma": 1}]}, {"featureType": "road.highway", "stylers": [{"hue": "#FFC200"}, {"saturation": -61.8}, {"lightness": 45.599999999999994}, {"gamma": 1}]}, {"featureType": "road.arterial", "stylers": [{"hue": "#FF0300"}, {"saturation": -100}, {"lightness": 51.19999999999999}, {"gamma": 1}]}, {"featureType": "road.local", "stylers": [{"hue": "#FF0300"}, {"saturation": -100}, {"lightness": 52}, {"gamma": 1}]}, {"featureType": "water", "stylers": [{"hue": "#0078FF"}, {"saturation": -13.200000000000003}, {"lightness": 2.4000000000000057}, {"gamma": 1}]}, {"featureType": "poi", "stylers": [{"hue": "#00FF6A"}, {"saturation": -1.0989010989011234}, {"lightness": 11.200000000000017}, {"gamma": 1}]}]
    };
    var image = 'https://cdn1.iconfinder.com/data/icons/mayssam/512/Location-24.png';
    var map = new google.maps.Map(mapCanvas, mapOptions);
    var marker = new google.maps.Marker({
        position: {lat: 38.254162, lng: -85.748349},
        map: map,
        icon: image
    });
}
google.maps.event.addDomListener(window, 'load', initialize);



$(document).ready(function () {
      
    $('#content').click(function (event) {

    
        var cont = $('#content').val();
            alert(cont);
            
            //
     });
     });