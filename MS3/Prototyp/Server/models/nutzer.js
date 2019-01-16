const mongoose = require('mongoose');

let NutzerSchema = new mongoose.Schema({
  name: {
    type: String,
    required: true,
    unique: true
  },
  karteikartenstapel:
  {
    name: String,
    ersteller: String
  },
  koordinaten:{
    langitude: Number,
    longitude: Number
  }
});

module.exports = mongoose.model('Nutzer', NutzerSchema);
