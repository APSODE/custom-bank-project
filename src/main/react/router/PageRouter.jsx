import React from "react";
import { BrowserRouter, Routes, Route, NavLink } from "react-router-dom";
import MainPage from "@/component/MainPage";
import TestPage from "@/component/TestPage";

export default function PageRouter() {
    return (
        <BrowserRouter>
            <nav>
                <NavLink className = { ({ isActive }) => "nav-link" + (isActive ? " click" : "") } to = "/">
                    MainPage
                </NavLink>
                <NavLink className = { ({ isActive }) => "nav-link" + (isActive ? " click" : "") } to = "/test">
                    TestPage
                </NavLink>
            </nav>
            <Routes>
                <Route exact path = "/" element = {<MainPage/>}/>
                <Route path = "/test" element = {<TestPage/>}/>
            </Routes>
        </BrowserRouter>
    )
};