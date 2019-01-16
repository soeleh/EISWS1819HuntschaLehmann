//Module
global.express = require('express');
global.app = express();
global.bodyParser = require('body-parser');
global.mongoose = require('mongoose');

const nutzerRoute = require('./routes/nutzer.js');
const kartenRoute = require('./routes/karteikarten.js');
const stapelRoute = require('./routes/karteikartenstapel.js');

//Middleware Anbindung
mongoose.connect(`mongodb://${Team21}:${chr1sundSoeren}@ds255754.mlab.com:55754/${calmrunter}`, { useNewUrlParser: true });
mongoose.connection.once('open', function(){
  console.log('MongoDB verfügbar');
});
const db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error:'));

//Initialisierung
app.use(bodyParser.json());

app.use(nutzerRoute);
nutzerRoute.init(app);

app.use(kartenRoute);
kartenRoute.init(app);

app.use(stapelRoute);
stapelRoute.init(app);

//Port
const port = process.env.port || 2019;
app.listen(port, console.info(`Server has started on ${port}`));
