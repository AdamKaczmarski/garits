import Form from "react-bootstrap/Form";

const AddUserForm = () => {
  return (
    <Form className="mt-3">
      <Form.Group controlId="formEmail">
        <Form.Label>Email address</Form.Label>
        <Form.Control type="email" />
      </Form.Group>
      <Form.Group controlId="firstName">
        <Form.Label>First Name</Form.Label>
        <Form.Control />
      </Form.Group>
      <Form.Group controlId="lastName">
        <Form.Label>Last Name</Form.Label>
        <Form.Control />
      </Form.Group>
      <Form.Group controlId="role">
        <Form.Label>Role</Form.Label>
        <Form.Select aria-label="roleSelect">
          <option>Role...</option>
          <option value="MECHANIC">Mechanic</option>
          <option value="FRANCHISEE">Franchisee</option>
          <option value="MECHANIC">Mechanic</option>
          <option value="FOREPERSON">Foreperson</option>
        </Form.Select>
      </Form.Group>
    </Form>
  );
};

export default AddUserForm;
