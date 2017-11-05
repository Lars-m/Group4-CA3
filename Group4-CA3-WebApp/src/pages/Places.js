import React, { Component } from 'react'
import adressData from "../facades/addressFacade";

class Places extends Component {

  constructor() {
    super();
    this.state = { data: [], err: "" }

    
  }

  componentWillMount() {
    adressData.getData((e, data) => {
      if (e) {
        return this.setState({ err: e.err })
      }
      this.setState({ err: "", data });
      
    });
  }


  render() {
    const places = this.state.data;

    return (
      <div className = "container">
        <h2>Places</h2>
            <div className="row">
                    {
                        places.map((place) => {
                            return (
                                <div key={place.id} className="box" style={{ width: 1000 }}>
                                    <img src={place.imageUrl} style={{ width: 500, height: 400}} />
                                    <div>
                                      <hr />
                                        <b> {place.city}, </b>
                                        <i>{place.description}</i>
                                        <p>Address: <b>{place.street.toUpperCase()}, {place.city.toUpperCase()}, {place.zip} </b> </p>
                                        <br />
                                    </div>
                                </div>
                            )
                        })
                    }
                </div>
        </div>
    )
  }
}

export default Places;