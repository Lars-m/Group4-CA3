import React, { Component } from 'react'
import adressData from "../facades/addressFacade";
import { Link } from 'react-router-dom';



class UserList extends Component {

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
    const listItems = this.state.data.map((d) => <li key ={d.city} >  {d.city} {d.street} {d.zip} {d.description}</li>);

    return (
      <div>
      <ul>
            {
                this.state.data.map(p => (
                    <li key={p.city}>
                    {p.city}
                      <Link to={`/userlist/${p.city}`}>edit</Link>
                    </li>
                ))
            }
        </ul>
      </div>
    )
  }
}

export default UserList;