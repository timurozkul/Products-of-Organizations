const express = require('express');

var app = express();

app.use("/css", express.static(__dirname + '/css'));
app.use("/public", express.static(__dirname + '/public'));
app.use("/index.js", express.static(__dirname + '/index.js'));

app.get('/', function(req, res) {
    res.sendFile('index.html', {root: __dirname })
});

app.get('/consulting', function(req, res) {
    res.sendFile('consulting/consulting.html', {root: __dirname })
});
app.get('/management', function(req, res) {
    res.sendFile('management/management.html', {root: __dirname })
});
app.get('/development', function(req, res) {
    res.sendFile('development/development.html', {root: __dirname })
});




app.listen(3000);