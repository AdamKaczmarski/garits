//import { Suspense } from "react";
import { Route, Routes } from "react-router-dom";
import { Container } from "react-bootstrap";

import Home from "./pages/Home";
import Login from "./pages/Login";

const App = () => {
  return (
    <Container>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="login" element={<Login />} />
        <Route path="*" element={<main><p>Nothing here</p></main>} />
      </Routes>
    </Container>
  );
};

export default App;
