import React, { Component } from 'react'
import adminData from "../facades/adminFacade";
import { confirmAlert } from 'react-confirm-alert';
import 'react-confirm-alert/src/react-confirm-alert.css'

class EditUser extends Component {
    constructor(){
      super();
      this.state = {data: "", err:""}
      
    }

    redirect = () => {
    this.props.history.push('/userlist');
    }

    handleSave = (event) => {
    event.preventDefault();
    this.redirect();
    }

    handleDelete = (event) => {
      event.preventDefault();
      confirmAlert({
        title: 'Confirm to submit',                        
        message: 'Are you sure you wish to delete this user',               
        childrenElement: () => <div>Custom UI</div>,       
        confirmLabel: 'Delete',                           
        cancelLabel: 'Cancel',                             
        onConfirm: () => this.deleteUser(),    
        onCancel: () => this.render(),      
      })
    }

    deleteUser = () => {
      adminData.deleteUser((err) => {
      if (err) {
        return this.setState({ err: err.errorMessage });
      }
        this.setState({ err: "" });
        this.props.history.push("/userlist");
     });
      
    }

    editUser = () => {
      ((err) => {
      if (err) {
        return this.setState({ err: err.errorMessage });
      }
        this.setState({ err: "" });
        render();
     });
    }

    componentWillMount() {
    }

    render() {
      return (
        <div>
          <h2>User : </h2>
          kaka
          <button className="btn btn-lg btn-primary btn-block" onClick={this.handleDelete}>Delete User</button>
          <button className="btn btn-lg btn-primary btn-block" onClick={this.handleSave}>Save</button>
            </div>
      )
    }


  }

export default EditUser;