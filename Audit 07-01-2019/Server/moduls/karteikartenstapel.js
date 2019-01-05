const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const karteikartenstapelSchema = new Schema({
  name: String,
  ersteller: String,
  anzahlKarten: Number,
  lerndauer: Number,
  karteikarte:{
    id: Number,
    ersteller: String,
    vorderseite: String,
    rueckseite: String,
    laenge: Number
  },
  tag: String,
  kategorie:{
      studienfach: String,
      modul: String
  }
});

const karteikartenstapel = mongoose.model('karteikartenstapel', karteikartenstapelSchema);

module.exports = karteikartenstapel;
