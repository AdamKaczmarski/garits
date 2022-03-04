//import { Suspense } from "react";
import { Route, Routes } from "react-router-dom";
import Container from "react-bootstrap/Container";

import Home from "./pages/Home";
import Inventory from "./pages/Inventory";
import Jobs from "./pages/Jobs";
import Login from "./pages/Login";
import Users from "./pages/Users";
import Customers from "./pages/Customers";
import Services from "./pages/Services";
import Payments from "./pages/Payments";

import NavigationBar from "./components/Navigation/NavigationBar";
import Footer from "./components/Footer/Footer";

const App = () => {
  return (
    <Container className="d-flex min-vh-100 flex-column">
      <NavigationBar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="login" element={<Login />} />
        <Route path="inventory" element={<Inventory />} />
        <Route path="payments" element={<Payments />} />
        <Route path="jobs" element={<Jobs />} />
        <Route path="users" element={<Users />} />
        <Route path="customers" element={<Customers />} />
        <Route path="services" element={<Services />} />
        <Route
          path="*"
          element={
            <main>
              <p>Nothing here</p>
            </main>
          }
        />
      </Routes>
      <Footer />
    </Container>
  );
};

export default App;
