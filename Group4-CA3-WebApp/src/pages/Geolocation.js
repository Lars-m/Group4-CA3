import React from 'react';
import ReactMapboxGl from 'react-mapbox-gl';
import '@mapbox/mapbox-gl-draw/dist/mapbox-gl-draw.css';
import DrawControl from 'react-mapbox-gl-draw';
import '../styles/App.css';

const Map = ReactMapboxGl({
  accessToken: 'pk.eyJ1IjoiYWxleDMxNjUiLCJhIjoiY2o0MHp2cGtiMGFrajMycG5nbzBuY2pjaiJ9.QDApU0XH2v35viSwQuln5w',
});

const Geolocation = () => {
  return (
 
      <Map
        style="mapbox://styles/mapbox/outdoors-v9" // eslint-disable-line
        containerStyle={{ height: '800px', width: '50vw' }}
      >
        <DrawControl />
      </Map>
    
  );
};

export default Geolocation;