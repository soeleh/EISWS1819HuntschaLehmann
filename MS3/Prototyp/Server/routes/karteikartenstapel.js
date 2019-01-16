let stapelModel = require('../models/karteikartenstapel.js');
let kartenModel = require('../models/karteikarten.js');
const router = express.Router();

//Ausgabe aller Karteikartenstapel
router.get('/stapel', function(req, res){
  stapelModel.find(function (err, stapel){
    if(err){
      return res.status(500).json({success: "false"});
    }
    else{
      return res.status(200).json({success:"true", stapel: stapel});
    }
  });
});

//Ausgabe eines Karteikartenstapels nach ID
router.get('/stapel/:id', function(req, res){
  stapelModel.find({id: req.params.id}, function(err, stapel){
    if(err){
      return res.status(500).json({success: "false"});
    }
    else{
      res.status(200).json({success:"true", stapel: stapel});
    }
  });
});

//Erstellen eines Karteikartenstapels
router.post('/stapel', function(req, res){
  if(!req.body || !req.params.id){
    return res.status(400).send("Request body is missing or Missing URL parameter: ID");
  }
  else{
    let model = new stapelModel(req.body);
    model.save();
  }
});

//Ändern eines Karteikartenstapels nach ID
router.put('/stapel/:id', function(req, res){
  if(!req.body || !req.params.id){
    return res.status(400).send("Request body is missing or Missing URL parameter: ID");
  }
  else{
    stapelModel.findOneAndUpdate({id: req.params.id}, req.body, {new: true}, function(err, stapel){
      if(err){
        return res.status(500).json({success: "false"});
      }
      else{
        //Berechnen der neuen Dauer
        let x = 0, y = 0;
        for(var i = 0; i < stapel.karteikarten.length; i++){ //Für jede ID in Karteikarte-Attribut von Stapel
          kartenModel.find({"id": stapel.karteikarten.id[i]}, function(err, karte){
            y += karte.laenge;
          });
        }
        stapel.lerndauer = y; //Gesamtlaenge wird neu gespeichert
        return res.status(200).json({success:"true", stapel: stapel});
      }
    });
  }
});

//Entfernen eines Karteikartenstapels nach ID
router.delete('/stapel/:id)', function(req, res){
  if(!req.params.id){
    return res.status(400).send("Missing URL parameter: ID");
  }
  else{
    stapelModel.findOneAndRemove({id: req.params.id}, function(err, stapel){
      if(err){
        return res.status(500).json({success: "false"});
      }
      else{
        return res.status(200).json({success:"true", stapel: stapel});
      }
    });
  }
});

module.exports = router;
