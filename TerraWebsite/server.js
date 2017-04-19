const express = require('express');

var app = express();

app.get('/', function(req, res) {
    res.sendfile('index.html', {root: __dirname })
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