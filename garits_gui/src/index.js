import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import App from "./App";
import { /* BrowserRouter, */ HashRouter } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';

ReactDOM.render(
  <HashRouter basename="/">
    <App />
  </HashRouter>,
  document.getElementById("root")
);
