import { useContext } from "react";
import Navbar from "react-bootstrap/Navbar";
import Nav from "react-bootstrap/Nav";
import Container from "react-bootstrap/Container";
import AuthContext from "../../store/auth-context";
import { Link } from "react-router-dom";

/*need to add conditions what should be shown for each role */

const NavigationBar = () => {
  const authCtx = useContext(AuthContext);
  const { authData, onLogout } = authCtx;

  let navLinks;
  if (authData.role === "ROLE_ADMIN") {
    navLinks = (
      <>
        <Nav.Link as={Link} to="users">
          Users
        </Nav.Link>
        <Nav.Link as={Link} to="login" onClick={onLogout}>
          Logout
        </Nav.Link>
      </>
    );
  } else if (authData.role === "ROLE_MECHANIC") {
    navLinks = (
      <>
        <Nav.Link as={Link} to="jobs">
          Jobs
        </Nav.Link>
        <Nav.Link as={Link} to="inventory">
          Inventory
        </Nav.Link>
        <Nav.Link as={Link} to="payments">
          Payments
        </Nav.Link>
        <Nav.Link as={Link} to="services">
          Services
        </Nav.Link>
        <Nav.Link as={Link} to="customers">
          Customers
        </Nav.Link>

        <Nav.Link as={Link} to="login" onClick={onLogout}>
          Logout
        </Nav.Link>
      </>
    );
  } else if (authData.role === "ROLE_FRANCHISEE") {
  } else if (authData.role === "ROLE_FOREPERSON") {
  } else if (authData.role === "ROLE_RECEPTIONIST") {
    navLinks = (
      <>
        <Nav.Link as={Link} to="jobs">
          Jobs
        </Nav.Link>
        <Nav.Link as={Link} to="inventory">
          Inventory
        </Nav.Link>
        <Nav.Link as={Link} to="payments">
          Payments
        </Nav.Link>

        <Nav.Link as={Link} to="customers">
          Customers
        </Nav.Link>

        <Nav.Link as={Link} to="login" onClick={onLogout}>
          Logout
        </Nav.Link>
      </>
    );
  } else {
    navLinks = (
      <Nav.Link as={Link} to="login">
        Login
      </Nav.Link>
    );
  }

  return (
    <Navbar
      sticky="top"
      bg="dark"
      variant="dark"
      className="w-100 "
      expand="md"
      style={{
        boxShadow: "0px 0 5px rgba(0, 0, 0, 0.5)",
        borderRadius: "6px 6px 6px 6px",
      }}
    >
      <Container fluid>
        <Navbar.Brand href="#/">GARITS </Navbar.Brand>
        <Navbar.Toggle aria-controls="navbar-nav" />

        <Navbar.Collapse id="navbar-nav">
          <Nav className="justify-content-end flex-grow-1 pe-3">
            {navLinks}{" "}
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
};

export default NavigationBar;
