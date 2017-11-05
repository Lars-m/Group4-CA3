import fetchHelper, {errorChecker} from "./fetchHelpers"
const URL = require("../../package.json").serverURL;


class AdminStore {
  constructor() {
    this._data = "";
    this._errorMessage = "";
  }

  getData = (cb) => {
    this._errorMessage = "";
    this._messageFromServer = "";
    let resFromFirstPromise=null;  //Pass on response the "second" promise so we can read errors from server
    const options = fetchHelper.makeOptions("GET", true);
    fetch(URL + "api/address", options)
      .then((res) => {
        resFromFirstPromise = res;
        return res.json();
      }).then((data) => {
        errorChecker(resFromFirstPromise,data);
        if (cb) {
          cb(null, data.message)
        }
      }).catch(err => {
        if (cb) {
          cb({ err: fetchHelper.addJustErrorMessage(err) })
        }
      })
  }

  
  getUsers = (cb) => {
    this._errorMessage = "";
    this._messageFromServer = "";
    let resFromFirstPromise=null;  //Pass on response the "second" promise so we can read errors from server
    const options = fetchHelper.makeOptions("GET", false);
    fetch(URL + "api/admin/users", options)
      .then((res) => {
        resFromFirstPromise = res;
        return res.json();
      }).then((data) => {
        errorChecker(resFromFirstPromise,data);
        if (cb) {
          cb(null, data)
          console.log(data);
        }
      }).catch(err => {
        if (cb) {
          cb({ err: fetchHelper.addJustErrorMessage(err) })
        }
      })
  }

    editUser = (cb, username) => {
    this._errorMessage = "";
    this._messageFromServer = "";
    let resFromFirstPromise=null;  //Pass on response the "second" promise so we can read errors from server
    const options = fetchHelper.makeOptions("PUT", true);
    fetch(URL + "api/admin/edit" + username , options)
      .then((res) => {
        resFromFirstPromise = res; 
        return res.json();
      }).then((data) => {
        errorChecker(resFromFirstPromise,data);
        if (cb) {
          cb(null, data)
        }
      }).catch(err => {
        if (cb) {
          cb({ err: fetchHelper.addJustErrorMessage(err) })
        }
      })
  }

    deleteUser = (cb, username) => {
    this._errorMessage = "";
    this._messageFromServer = "";
    let resFromFirstPromise=null;  //Pass on response the "second" promise so we can read errors from server
    const options = fetchHelper.makeOptions("DELETE", true);
    fetch(URL + "api/admin/delete" + username, options)
      .then((res) => {
        resFromFirstPromise = res;
        return res.json();
      }).then((data) => {
        errorChecker(resFromFirstPromise,data);
        cb(null)
      }).catch(err => {
        cb({ err: fetchHelper.addJustErrorMessage(err) })
      })
  }

}

let adminStore = new AdminStore();

//Only for debugging
//window.userStore = userStore;
export default adminStore;