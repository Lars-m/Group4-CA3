import fetchHelper, {errorChecker} from "./fetchHelpers"
const URL = require("../../package.json").serverURL;


class AddressStore {
  constructor() {
    this._data = "";
    this._errorMessage = "";
  }

  getData = (cb) => {
    this._errorMessage = "";
    this._messageFromServer = "";
    let resFromFirstPromise=null;  //Pass on response the "second" promise so we can read errors from server
    const options = fetchHelper.makeOptions("GET", false);
    fetch(URL + "api/place", options)
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
}

let addressStore = new AddressStore();

//Only for debugging
//window.userStore = userStore;
export default addressStore;