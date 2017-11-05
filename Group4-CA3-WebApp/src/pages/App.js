import React from "react"
import {Route, Switch } from "react-router-dom"
import Login from "./Login";
import Logout from "./Logout";
import About from "./About";
import UserPage from "./UserPage";
import AdminPage from "./AdminPage";
import TopMenu from "./TopMenu";
import Register from "./Register";
<<<<<<< HEAD
import Places from "./Places";
=======
import Address from "./Address";
import Geolocation from "./Geolocation";
>>>>>>> 6faa7d546ec095c95e57352c9ecf074ad93cb860


function App() {
  return (
    <div>
      <TopMenu />
      <Switch>
        <Route path="/login" component={Login} />
        <Route path="/logout" component={Logout} />
        <Route path="/about" component={About} />
        <Route path="/user" component={UserPage} />
        <Route path="/admin" component={AdminPage} />
        <Route path="/register" component={Register} />
<<<<<<< HEAD
        <Route path="/places" component={Places} />
=======
        <Route path="/address" component={Address} />
        <Route path="/gps" component={Geolocation} />
>>>>>>> 6faa7d546ec095c95e57352c9ecf074ad93cb860
      </Switch>
    </div>
  )
}
export default App;