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
import { useEffect, useState, useCallback } from "react";
import axios from "axios";
import NotificationModalPayments from "./components/Notifications/NotificationModalPayments";
import NotificationModalStock from "./components/Notifications/NotificationModalStock";
const App = () => {
  const [show, setShow] = useState(true);
  const handleShow = () => setShow(!show);
  const [show2, setShow2] = useState(true);
  const handleShow2 = () => setShow2(!show2);

  const [latePaymentCustomers, setLatePaymentCustomers] = useState([]);
  const [lowStockParts, setLowStockParts] = useState([]);
  const obtainLatePaymentCustomers = useCallback(async () => {
    try {
      const response = await axios({
        method: "GET",
        url: "http://localhost:8080/customers/late-payments",
      });
      console.log(response);
      if (response.status === 200) setLatePaymentCustomers(response.data);
    } catch (err) {
      console.log(err);
    } finally {
      if (latePaymentCustomers && latePaymentCustomers.length > 0) {
        setShow(true);
      }
    }
  }, []);
  const obtainLowStockParts = useCallback(async () => {
    try {
      const response = await axios({
        method: "GET",
        url: "http://localhost:8080/parts/low-stock",
      });
      console.log(response);
      if (response.status === 200) setLowStockParts(response.data);
    } catch (err) {
      console.log(err);
    } finally {
      console.log(lowStockParts);
      if (lowStockParts && lowStockParts.length > 0) {
        setShow2(true);
      }
    }
  }, []);
  useEffect(() => {
    obtainLowStockParts();
    obtainLatePaymentCustomers();
  }, [obtainLatePaymentCustomers, obtainLowStockParts]);
  return (
    <Container
      className="d-flex min-vh-100 flex-column"
      style={{ maxWidth: "90%" }}
    >
      <NavigationBar />
      <Routes>
        <Route path="/" element={<Jobs />} />
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
      <NotificationModalPayments
        show={show}
        onClose={handleShow}
        latePaymentCustomers={latePaymentCustomers}
      />
      <NotificationModalStock
        show={show2}
        onClose={handleShow2}
        lowStockParts={lowStockParts}
      />
    </Container>
  );
};

export default App;
