import Card from "react-bootstrap/Card";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";

const LoginForm = () => {

  /**
   * API REQUESTS
   * VALIDATION
   */
  return (
    <Card className="mt-5 mb-auto mx-auto" style={{width:'32rem'}}>
      <Card.Body className=" mx-3">
        <Card.Title>Log in</Card.Title>
      </Card.Body>
      <Form className="mx-3 mb-3">
        <Form.Group className="mb-3" controlId="formBasicEmail">
          <Form.Label>Email address</Form.Label>
          <Form.Control type="email" placeholder="Enter email" />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicPassword">
          <Form.Label>Password</Form.Label>
          <Form.Control type="password" placeholder="Password" />
        </Form.Group>
        <Button variant="primary" type="submit">
          Login
        </Button>
      </Form>
    </Card>
  );
};

export default LoginForm;
