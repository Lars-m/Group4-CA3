import React, { Component } from 'react';
import placesFacade from '../facades/addressFacade';

export default class AddPlace extends Component
{
    constructor(props) {
        super(props);
            this.state = {
                place: {
                    name: "",
                    description: "",
                    street: "",
                    city: "",
                    zip: "",
                    rating: "",
                    file: null
                }
            };
    }

    _submitHandler = e => {
        e.preventDefault();

        let place = this.state.place;

        placesFacade.addPlace(place);
        console.log("reached.");
    }

    onChangeHandler = e => {
        e.preventDefault();
        let target = e.target;
        let name = target.name;
        let value = target.value;

        

        this.setState(prevState => (
            {
                place: { ...prevState.place, [name]: value },
                isDirty: true
            }
        ));
    }

    render(){
        return (
            <div className="">
                <form className="form-inline" onSubmit={this._submitHandler}>
                    Name: <input name="name" type="text" onChange={this.onChangeHandler} value={this.state.place.name} required />
                    City: <input name="city" type="text" onChange={this.onChangeHandler} value={this.state.place.city} required />
                    Description: <input name="description" type="text" onChange={this.onChangeHandler} value={this.state.place.description} required />
                    Street: <input name="street" type="text" onChange={this.onChangeHandler} value={this.state.place.street} required />
                    Zip: <input name="zip" type="text" onChange={this.onChangeHandler} value={this.state.place.zip} required />
                    Rating: <input name="rating" type="number" onChange={this.onChangeHandler} value={this.state.place.rating} min="0" max="5" required />
                    Image: <input type="file" name="file" onChange={this.onChangeHandler} value = {this.state.place.file}/>

                    <input type="submit" value="Save the place" />
                </form>
            </div>
        );
    }
    
}