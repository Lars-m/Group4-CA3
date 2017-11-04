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
    const listItems = this.state.data.map((d) => <li key>  {d.city} {d.street} {d.zip} {d.description}</li>);

    return (
      <div>
        <h2>Address</h2>
        <p>List of addresses</p>
        <div>
       
            {listItems}
        
          
        </div>
      </div>
    )
  }
}

export default Address;