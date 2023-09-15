import React from "react";
import ReactDom from "react-dom";

function render() {
    ReactDom.render(
        <h2>
            싯팔 리액트 적용하기 뭐이리 까다롭냐
        </h2>,
        document.getElementById("root")
    )
}

render()
