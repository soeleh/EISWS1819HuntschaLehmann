const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const KarteikarteSchema = new Schema({
  id: {
    type: Number,
    unique: true,
    required: true
  },
  ersteller: String,
  vorderseite: String,
  rueckseite: String,
  laenge: Number,
  prio: {
    type: Number,
    required: true
  }
});

module.exports = mongoose.model('karteikarte', KarteikarteSchema);
