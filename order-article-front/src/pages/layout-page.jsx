import * as React from "react";
import { BrowserRouter, Routes, Route, Outlet } from "react-router-dom";
import ErrorPage from "../pages/error-page.jsx";
import { ProtectedRoute } from "../routes/ProtectedRoute";
import IndexClient from "../clients/components/IndexClient.jsx";
import IndexArticle from "../articles/components/IndexArticle.jsx";
import IndexOrders from "../orders/components/IndexOrders.jsx";

function LayoutPage() {
  return (
    <BrowserRouter>
      <Routes>
        <Route element={<ProtectedRoute />}>
          <Route path="/" element={<IndexOrders />} />
          <Route path="clients" element={<IndexClient />} />
          <Route path="articles" element={<IndexArticle />} />
        </Route>

        <Route element={<ProtectedRoute />}>
          <Route path="*" element={<ErrorPage />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default LayoutPage;
