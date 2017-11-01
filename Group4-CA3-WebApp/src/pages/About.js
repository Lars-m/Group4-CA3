import React, {Component} from "react";

export default class About extends Component{
  render() {
    fetch('http://localhost:8080/seedMaven/api/address')
    
    return <h1>About</h1>
  }
}