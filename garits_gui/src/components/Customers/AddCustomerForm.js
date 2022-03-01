import Form from "react-bootstrap/Form";

const AddUserForm = () => {
  return (
    <Form className="mt-3">
      <Form.Group controlId="formEmail">
        <Form.Label>Email address</Form.Label>
        <Form.Control type="email" />
      </Form.Group>
      <Form.Group controlId="name">
        <Form.Label>Name</Form.Label>
        <Form.Control />
      </Form.Group>
      <Form.Group controlId="address">
        <Form.Label>Address</Form.Label>
        <Form.Control />
      </Form.Group>
      <Form.Group controlId="postcode">
        <Form.Label>Postcode</Form.Label>
        <Form.Control />
      </Form.Group>
      <Form.Group controlId="tel_no">
        <Form.Label>Telephone Number</Form.Label>
        <Form.Control />
      </Form.Group>
      <Form.Group controlId="fax">
        <Form.Label>FAX</Form.Label>
        <Form.Control />
      </Form.Group>
      <Form.Group controlId="fixed_discount">
        <Form.Label>Fixed Discount</Form.Label>
        <Form.Control type="number" min={0} max={100} />
      </Form.Group>
      <Form.Group controlId="flex_discount">
        <Form.Label>Flexible Discount</Form.Label>
        <Form.Control />
      </Form.Group>
      <Form.Group controlId="service_discount">
        <Form.Label>Service Discount</Form.Label>
        <Form.Control />
      </Form.Group>
    </Form>
  );
};

export default AddUserForm;
