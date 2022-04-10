import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import App from "./App";
import { /* BrowserRouter, */ HashRouter } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import { AuthContextProvider } from "./store/auth-context";

ReactDOM.render(
  <AuthContextProvider>
    <HashRouter basename="/">
      <App />
    </HashRouter>
  </AuthContextProvider>,
  document.getElementById("root")
);
