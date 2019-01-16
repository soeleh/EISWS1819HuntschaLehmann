const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const StapelSchema = new mongoose.Schema({
  id: {
    type: Number,
    required: true,
    unique: true
  },
  name: String,
  anzahlKarten: Number,
  lerndauer: Number,
  karteikarten:{
    id: Number
  },
  tag: String,
  kategorie:{
      studienfach: String,
      modul: String
  }
});

module.exports = mongoose.model('karteikartenstapel', StapelSchema);
