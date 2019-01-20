let stapelModel = require('../models/karteikartenstapel.js');
let kartenModel = require('../models/karteikarten.js');
const router = express.Router();

//_________________________________________
//Ausgabe aller Karteikartenstapel
router.get('/stapel', function(req, res){
  stapelModel.find(function (err, stapel){
    if(err){
      res.status(500).json({success: "false"});
    }
    else{
      res.status(200).json({success:"true", stapel: stapel});
    }
  });
});

//_________________________________________
//Ausgabe eines Karteikartenstapels nach ID
router.get('/stapel/:id', function(req, res){
  if(!req.params.id){
    return res.status(400).send("Missing URL parameter: ID");
  }
  else{
    stapelModel.find({id: req.params.id}, function(err, stapel){
      if(err){
        res.status(500).json({success: "false"});
      }
      else{
        res.status(200).json({success:"true", stapel: stapel});
      }
    });
  }
});

//_________________________________________
//Erstellen eines Karteikartenstapels
router.post('/stapel', function(req, res){
  if(!req.body){
    return res.status(400).send("Request body is missing");
  }
  else{
    let model = new stapelModel(req.body);
    model.save();
    res.status(200).json({success:"true"});
  }
});

//_________________________________________
//Ändern eines Karteikartenstapels
//Anwendungslogik: Update der Lerndauer
router.put('/stapel/:id',function(req,res){
  if(!req.body || !req.params.id){
    return res.status(400).send("Request body is missing or Missing URL parameter: ID");
  }
  else{
    stapelModel.find({id:req.params.id},function(err,stapel){
      if(err){
        return res.status(500).json({success:"false"});
      }
      else{
        var stapel = stapel[0];
        stapel.lerndauer = 0; //Zurücksetzen der Lerndauer
        var indexCards = req.body.karteikarten;

        for(var i = 0; i < indexCards.length;i++){
          if(err){
          }
          else{
            stapel.karteikarten[i].laenge = indexCards[i].laenge;
            stapel.lerndauer+= Number(indexCards[i].laenge); //wenn Lerndauer gesetzt ist wird die Funktion UpdateStapel aufgerufen
            UpdateStapel(stapel,res); //Res übergeben damit im Update später die Response geschickt werden kann
          }
        }
      }
    });
  }
});
//_________________________________________
//Ändert Neuerungen an einem Stapel
function UpdateStapel(stapel, res){
  stapelModel.findOneAndUpdate({id: stapel.id}, stapel, {new: true}, function(err, doc){
    if(err){
      res.status(500).json({success: "false"});
    }
    else{
      res.status(200).json({success:"true", doc: doc});
    }
  });
}

//_________________________________________
//Entfernen eines Karteikartenstapels nach ID
router.delete('/stapel/:id', function(req, res){
  if(!req.params.id){
    return res.status(400).send("Missing URL parameter: ID");
  }
  else{
    stapelModel.findOneAndRemove({id: req.params.id}, function(err, stapel){
      if(err){
        res.status(500).json({success: "false"});
      }
      else{
        res.status(200).json({success:"true", stapel: stapel});
      }
    });
  }
});

module.exports = router;
