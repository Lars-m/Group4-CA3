import React, { Component } from 'react'
import adminData from "../facades/adminFacade";
import { Link } from 'react-router-dom';


class UserList extends Component {

  constructor() {
    super();
    this.state = { data: [], err: "" }
  }

  componentWillMount() {
    adminData.getUsers((e, data) => {
      if (e) {
        return this.setState({ err: e.err })
      }
      this.setState({ err: "", data });
    });
  }

     render() {
    const listItems = this.state.data.map((d) => <li key ={d.username} >  {d.username} </li>);

    return (
      <div>
      <ul>
            {
                this.state.data.map(p => (
                    <li key={p.username}>
                    {p.username}
                      <Link to={`/userlist/${p.username}`} activeClassName="active">edit</Link>
                    </li>
                ))
            }
        </ul>
      </div>
    )
  } 
}

export default UserList;