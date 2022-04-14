import { useEffect, useState, useCallback, useContext } from "react";
//import { Suspense } from "react";
import { Route, Routes } from "react-router-dom";
import Container from "react-bootstrap/Container";

import Inventory from "./pages/Inventory";
import Jobs from "./pages/Jobs";
import Login from "./pages/Login";
import Users from "./pages/Users";
import Customers from "./pages/Customers";
import Services from "./pages/Services";
import Payments from "./pages/Payments";
import Reports from "./pages/Reports";

import NavigationBar from "./components/Navigation/NavigationBar";
import Footer from "./components/Footer/Footer";
import axios from "axios";
import NotificationModalPayments from "./components/Notifications/NotificationModalPayments";
import NotificationModalStock from "./components/Notifications/NotificationModalStock";
import AuthContext from "./store/auth-context";
const App = () => {
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);
  const [show2, setShow2] = useState(false);
  const handleShow2 = () => setShow2(!show2);
  const [latePaymentCustomers, setLatePaymentCustomers] = useState([]);
  const [lowStockParts, setLowStockParts] = useState([]);
  const authCtx = useContext(AuthContext);
  const obtainLatePaymentCustomers = useCallback(async () => {
    try {
      const response = await axios({
        method: "GET",
        url: "http://localhost:8080/customers/late-payments",
        headers: { Authorization: `Bearer ${authCtx.authData.token}` },
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
  }, [authCtx]);
  const obtainLowStockParts = useCallback(async () => {
    try {
      const response = await axios({
        method: "GET",
        url: "http://localhost:8080/parts/low-stock",
        headers: { Authorization: `Bearer ${authCtx.authData.token}` },
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
  }, [authCtx]);
  useEffect(() => {
    if (
      authCtx.authData.role !== "ROLE_MECHANIC" && authCtx.authData.role !== "ROLE_ADMIN" && 
      authCtx.authData.token !== null
    ) {
      obtainLowStockParts();
      obtainLatePaymentCustomers();
    }
  }, [obtainLatePaymentCustomers, obtainLowStockParts, authCtx]);
  let routes;
  if (authCtx.authData.role === "ROLE_MECHANIC") {  
    routes = (
      <Routes>
        <Route exact path="/" element={<Login />} />
        <Route path="login" element={<Login />} />
        <Route path="inventory" element={<Inventory />} />
        <Route path="jobs" element={<Jobs />} />
        <Route
          path="*"
          element={
            <main>
              <p>Nothing here</p>
            </main>
          }
        />
      </Routes>
    );
  } else if (
    authCtx.authData.role === "ROLE_RECEPTIONIST" ||
    authCtx.authData.role === "ROLE_FOREPERSON"
  ) {
    routes = (
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="login" element={<Login />} />
        <Route path="inventory" element={<Inventory />} />
        <Route path="jobs" element={<Jobs />} />
        <Route path="payments" element={<Payments />} />
        <Route
          path="*"
          element={
            <main>
              <p>Nothing here</p>
            </main>
          }
        />
      </Routes>
    );
  } else if (authCtx.authData.role === "ROLE_FRANCHISEE") {
    routes = (
      <Routes>
        <Route path="/" element={<Login />} />
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
    );
  } else if (authCtx.authData.role === "ROLE_ADMIN") {
    routes = (
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="login" element={<Login />} />
        <Route path="users" element={<Users />} />
        <Route
          path="*"
          element={
            <main>
              <p>Nothing here</p>
            </main>
          }
        />
      </Routes>
    );
  } else {
    routes = (
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="login" element={<Login />} />
        <Route
          path="*"
          element={
            <main>
              <p>Nothing here</p>
            </main>
          }
        />
      </Routes>
    );
  }
  return (
    <Container
      className="d-flex min-vh-100 flex-column"
      style={{ maxWidth: "90%" }}
    >
      <NavigationBar />
        <Routes>
        <Route path="/" element={<Login />} />
        <Route path="login" element={<Login />} />
        <Route path="inventory" element={<Inventory />} />
        <Route path="payments" element={<Payments />} />
        <Route path="jobs" element={<Jobs />} />
        <Route path="users" element={<Users />} />
        <Route path="customers" element={<Customers />} />
        <Route path="services" element={<Services />} />
        <Route path="reports" element={<Reports />} />
        <Route
          path="*"
          element={
            <main>
              <p>Nothing here</p>
            </main>
          }
        />
      </Routes> 
      {/* routes */}
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
      />{" "}
    </Container>
  );
};

export default App;
