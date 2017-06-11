const express = require('express');
const path = require('path');

var app = express();

app.use(express.static(path.join(__dirname, 'public')));

app.get('/', function(req, res) {
    res.sendFile('index.html', {root: __dirname })
});

app.get('/management', function(req, res) {
    res.sendFile('management.html', {root: __dirname })
});
app.get('/consulting', function(re, res) {
    res.sendFile('consulting.html', {root: __dirname })
});
app.get('/development', function(req, res) {
    res.sendFile('development.html', {root: __dirname })
});
app.get('/about', function(req, res) {
    res.sendFile('about.html', {root: __dirname })
});


app.listen(3000);