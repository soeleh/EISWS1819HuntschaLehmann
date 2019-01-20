let nutzerModel = require('../models/nutzer.js');
const router = express.Router();

//_________________________________________
//Ausgabe aller Nutzer
router.get('/nutzer', function(req, res){
  nutzerModel.find(function (err, nutzer){
    if(err){
      return res.status(500).json({success: "false"});
    }
    else{
      return res.status(200).json({success:"true", nutzer: nutzer});
    }
  });
});

//_________________________________________
//Ausgabe einer Nutzer nach Name
router.get('/nutzer/:name', function(req, res){
  nutzerModel.find({name: req.params.name}, function (err, nutzer){
    if(err){
      return res.status(500).json({success: "false"});
    }
    else{
      return res.status(200).json({success:"true", nutzer: nutzer});
    }
  });
});

//_________________________________________
//Erstellen eines Nutzers
router.post('/nutzer', function(req, res){
  if(!req.body){
    return res.status(400).send("Request body is missing");
  }
  else{
    let model = new nutzerModel(req.body);
    model.save();
    return res.status(200).json({success:"true"});
  }
});

//_________________________________________
//Ändern eines Nutzers nach Name
router.put('/nutzer/:name', function(req, res){
  if(!req.body || !req.params.name){
    return res.status(400).send("Request body is missing or Missing URL parameter: name");
  }
  else{
    nutzerModel.findOneAndUpdate({name: req.params.name}, req.body, {new: true}, function(err, nutzer){
      if(err){
        return res.status(500).json({success: "false"});
      }
      else{
        return res.status(200).json({success:"true", nutzer: nutzer});
      }
    });
  }
});

//_________________________________________
//Entfernen eines Nutzers nach Name
router.delete('/nutzer/:name', function(req, res){
  if(!req.params.name){
    return res.status(400).send("Missing URL parameter: name");
  }
  else{
    nutzerModel.findOneAndRemove({name: req.params.name}, function(err, nutzer){
      if(err){
        return res.status(500).json({success: "false"});
      }
      else{
        return res.status(200).json({success:"true", nutzer: nutzer});
      }
    });
  }
});

module.exports = router;
