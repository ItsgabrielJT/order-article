import React from "react";
import ReactDOM from "react-dom/client";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import "./index.css";

import LayoutPage from "./pages/layout-page.jsx";


ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <LayoutPage/>
  </React.StrictMode>
);
