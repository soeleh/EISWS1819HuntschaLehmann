//Module
var bodyParser = require('body-parser');
var express = require('express');
var mongoose = require('mongoose');

var app = express();
app.use(bodyParser.json());

var config = require('./config/app.config.js');

var server = require('http').Server(app);

//Port
var port = process.env.prodPort || 2019;
app.set('port', port);

//Middleware Anbindung
mongoose.connect('mongodb://localhost/calmRunter', { useNewUrlParser: true });
mongoose.connection.once('open', function(){
  console.log('MongoDB verfügbar');
});
var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error:'));

app.get('/', function(req, res){
  res.send('ok');
});

server.listen(app.get('port'));
