// const React = require("react")
// const ReactDom = require("react-dom")

import React from "react";
import ReactDom from "react-dom"
import MainPage from "./component/MainPage";
import PageRouter from "@/router/PageRouter";

function render() {
    ReactDom.render(
        <div className="App">
            <PageRouter/>
        </div>,
        document.getElementById("root")
    )
}

render()
