let kartenModel = require('../models/karteikarten.js');
const router = express.Router();

//_________________________________________
//Ausgabe aller Karteikarten
router.get('/karteikarten', function(req, res){
  kartenModel.find(function (err, karte){
    if(err){
      return res.status(500).json({success: "false"});
    }
    else{
      return res.status(200).json({success:"true", karte: karte});
    }
  });
});

//_________________________________________
//Ausgabe einer Karteikarte nach ID
router.get('/karteikarten/:id', function(req, res){
  if(!req.params.id){
    return res.status(400).send("Missing URL parameter: ID");
  }
  else{
    kartenModel.find({id: req.params.id}, function (err, karte){
      if(err){
        return res.status(500).json({success: "false"});
      }
      else{
        return res.status(200).json({success:"true", karte: karte});
      }
    });
  }
});

//_________________________________________
//Erstellen einer Karteikarte
router.post('/karteikarten', function(req, res){
  if(!req.body){
    return res.status(400).send("Request body is missing");
  }
  else{
    let model = new kartenModel(req.body);
    model.save();
    return res.status(200).json({success:"true"});
  }
});

//_________________________________________
//Ändern einer Karteikarte nach ID
router.put('/karteikarten/:id', function(req, res){
  if(!req.body || !req.params.id){
    return res.status(400).send("Request body is missing or Missing URL parameter: ID");
  }
  else{
    kartenModel.findOneAndUpdate({id: req.params.id}, req.body, {new: true}, function(err, karte){
      if(err){
        return res.status(500).json({success: "false"});
      }
      else{
        return res.status(200).json({success:"true", karte: karte});
      }
    });
  }
});

//_________________________________________
//Entfernen einer Karteikarte nach ID
router.delete('/karteikarten/:id', function(req, res){
  if(!req.params.id){
    return res.status(400).send("Missing URL parameter: ID");
  }
  else{
    kartenModel.findOneAndRemove({id: req.params.id}, function(err, karte){
      if(err){
        return res.status(500).json({success: "false"});
      }
      else{
        return res.status(200).json({success:"true", karte: karte});
      }
    });
  }
});

module.exports = router;
