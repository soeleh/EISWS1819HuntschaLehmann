//Module
global.express = require('express');
global.app = express();
global.bodyParser = require('body-parser');
global.mongoose = require('mongoose');

let nutzerRoute = require('./routes/nutzer.js');
let kartenRoute = require('./routes/karteikarten.js');
let stapelRoute = require('./routes/karteikartenstapel.js');

//Middleware Anbindung
/*
Nutzername: soelehm
Passwort: chr1sundSoeren
*/
mongoose.connect("mongodb://team21:chr1sundSoeren@ds255754.mlab.com:55754/calmrunter", {useNewUrlParser: true}, {auth:{authdb:"admin"}} );

//mongoose.connect('mongodb://localhost/calmrunter', { useNewUrlParser: true }); //lokal
mongoose.connection.once('open', function(){
  console.log('MongoDB verfügbar.');
});
const db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error:'));

//Initialisierung
app.use(bodyParser.json());

app.use(nutzerRoute);
app.use(kartenRoute);
app.use(stapelRoute);

//Port Einstellungen
const port = process.env.PORT || 2019;
app.listen(port, console.info(`Server läuft auf Port ${port}`));
