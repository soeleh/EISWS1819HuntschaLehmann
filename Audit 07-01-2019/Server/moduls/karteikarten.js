const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const karteikarteSchema = new Schema({
  id: Number,
  ersteller: String,
  vorderseite: String,
  rueckseite: String,
  laenge: Number
});

const karteikarte = mongoose.model('karteikarte', karteikarteSchema);

module.exports = karteikarte;
