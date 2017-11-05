import React, { Component } from 'react'
import adressData from "../facades/addressFacade";

class Address extends Component {

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
    const listItems = this.state.data.map((d) => <li key>  {d.city} {d.street} {d.zip} {d.description} <img src = {d.imageUrl} alt = 'image'/></li>);
    
    return (
      <div>
        <h2>Places</h2>
        <div>
            {listItems}
        </div>
      </div>
    )
  }
}

export default Address;