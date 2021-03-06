import Card from "react-bootstrap/Card";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { useContext, useEffect } from "react";
import AuthContext from "../../store/auth-context";
const LoginForm = () => {
  const authCtx = useContext(AuthContext);
  let navigate = useNavigate();
  const loginData = {
    email: "",
    password: "",
  };

  const login = async (ev) => {
    let role;
    ev.preventDefault();
    try {
      const response = await axios({
        method: "POST",
        url: "http://localhost:8080/auth/login",
        data: loginData,
        headers:{'Authorization': `Bearer ${authCtx.authData.token}`}
      });
      if (response.status === 200) {
        role = response.data.roles[0];
        authCtx.onLogin(response.data);
      }
    } catch (err) {
      console.log(err);
    } finally {
      if (role === "ROLE_ADMIN") navigate("/users", { replace: true });
      else if (role === "ROLE_MECHANIC") navigate("/jobs", { replace: true });
      else if (role === "ROLE_FRANCHISEE") navigate("/jobs", { replace: true });
      else if (role === "ROLE_FOREPERSON") navigate("/jobs", { replace: true });
      else if (role === "ROLE_RECEPTIONIST") navigate("/jobs", { replace: true });
      else {
        navigate("/login", { replace: true });
      }
    }
  };
  useEffect(()=>{
    if (authCtx.authData.token!==null){
     // authCtx.onLogout();
    }
  },[authCtx])
  const emailHandler = (ev) => {
    loginData.email = ev.target.value;
  };
  const passwordHandler = (ev) => {
    loginData.password = ev.target.value;
  };
  return (
    <Card className="mt-5 mb-auto mx-auto" style={{ width: "32rem" }}>
      <Card.Body className=" mx-3">
        <Card.Title>Log in</Card.Title>
      </Card.Body>
      <Form className="mx-3 mb-3">
        <Form.Group className="mb-3" controlId="formBasicEmail">
          <Form.Label>Login</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter login"
            onChange={emailHandler}
          />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicPassword">
          <Form.Label>Password</Form.Label>
          <Form.Control
            type="password"
            placeholder="Password"
            onChange={passwordHandler}
          />
        </Form.Group>
        <Button variant="primary" type="submit" onClick={login}>
          Login
        </Button>
      </Form>
    </Card>
  );
};

export default LoginForm;
