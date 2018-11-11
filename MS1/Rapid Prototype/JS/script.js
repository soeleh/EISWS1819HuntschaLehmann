
var map, service, count, duration, pos, placeLoc, i;
var markers = [];

//Creates Map.
function initMap() {
  map = new google.maps.Map(document.getElementById('map'), {
      zoom: 13
  });

  //Variables for displaying directions.
  var directionsService = new google.maps.DirectionsService;
  var directionsDisplay = new google.maps.DirectionsRenderer;

  var infoWindow = new google.maps.InfoWindow({ map: map });

  //Gets the GPS position.
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(function (position) {
      pos = {
        lat: position.coords.latitude,
        lng: position.coords.longitude
      };
      infoWindow.setPosition(pos);
      infoWindow.setContent('Standort gefunden.');
      map.setCenter(pos);
      //Sets marker on current position.
      var marker = new google.maps.Marker({
          map: map,
          position: {lat: pos.lat, lng: pos.lng}
      });
    }, function () {
            handleLocationError(true, infoWindow, map.getCenter());
    });
  }
  else {
    handleLocationError(false, infoWindow, map.getCenter());
  }
  directionsDisplay.setMap(map);

  var onChangeHandler = function(){

    //Reset Markers.
    for(var j = 0; j < markers.length; j++){
        markers[j].setMap(null);
    }

    count = (document.getElementById('end').value)*60; //60 is converting the Result in Seconds.
    duration = count * 1.4 / 2; //Average Human Walking Speed is 1.4m/s.

    service = new google.maps.places.PlacesService(map);

    service.nearbySearch({
        location: {lat: pos.lat, lng: pos.lng},
        radius : duration*1.05,
        type : ['place']
    }, callback);

    //Creates a Marker for every place.
    function callback(results, status) {
      if (status === google.maps.places.PlacesServiceStatus.OK) {
        for (var i = 0; i < results.length; i++) {
          createMarker(results[i]);
        }
      }
    }

    function createMarker(place) {
      placeLoc = place.geometry.location;
      //Add Marker to Array.
      marker = new google.maps.Marker({
        map : map,
        animation: google.maps.Animation.DROP,
        position : placeLoc
      });

      markers.push(marker);
      //On-Click Event displays the Direction from the User's GPS-Position.
      google.maps.event.addListener(marker, 'click', function(event) {
        calculateAndDisplayRoute(pos, event.latLng, directionsService, directionsDisplay);
      });
    }

    //Selects excluded Places.
    service.nearbySearch({
        location: {lat: pos.lat, lng: pos.lng},
        radius : duration*0.95,
        type : ['place']
    }, callback2);

    function callback2(removeResults, status) {
      if (status === google.maps.places.PlacesServiceStatus.OK) {
        for (var i = 0; i < results.length; i++) {
            removeMarker(removeResults[i]);
        }
      }
    }
    //Excludes Markers which are too close.
    function removeMarker(place) {
      for(var j = 0; j < markers.length; j++){
        if(markers[j].position == place.geometry.location){
          markers[j].setMap(null);
        }
      }
    }
  };

  //Listener for changing the selection field.
  document.getElementById('end').addEventListener('change', onChangeHandler);

  //Calculates a Route for Walking.
  function calculateAndDisplayRoute(myPosition, location, directionsService, directionsDisplay){
      directionsService.route({
        origin: myPosition,
        destination: location,
        travelMode: 'WALKING'
      }, function(response, status) {
        if (status === 'OK') {
          directionsDisplay.setDirections(response);
        } else {
          window.alert('Directions request failed due to ' + status);
        }
      });
  }
}
