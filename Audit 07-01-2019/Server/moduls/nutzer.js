const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const nutzerSchema = new Schema({
  nutzername: String,
  karteikartenstapel:
  {
    name: String,
    ersteller: String
  },
  koordination:{
    langitude: Number,
    longitude: Number
  }
});

const nutzer = mongoose.model('nutzer', nutzerSchema);

module.exports = nutzer;
