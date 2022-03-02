import Form from "react-bootstrap/Form";

const AddServiceForm = () => {
  return (
    <Form className="mt-3">
      <Form.Group controlId="name">
        <Form.Label>Service name</Form.Label>
        <Form.Control />
      </Form.Group>
      <Form.Group controlId="price">
        <Form.Label>Service price</Form.Label>
        <Form.Control type="number" step="0.01" min="0" />
      </Form.Group>
      <Form.Group controlId="lastName">
        <Form.Label>Last Name</Form.Label>
        <Form.Control />
      </Form.Group>
      <Form.Group controlId="desc">
        <Form.Label>Short description</Form.Label>
        <Form.Control as="textarea" rows={3}/>
      </Form.Group>
      <Form.Group controlId="time">
        <Form.Label>Approx. time</Form.Label>
        <Form.Control type="number" min="0" />
      </Form.Group>
    </Form>
  );
};

export default AddServiceForm;
