import Form from "react-bootstrap/Form";

const AddToInventoryForm = () => {
  return (
    <Form >
      <Form.Group controlId="partName">
        <Form.Label>Name</Form.Label>
        <Form.Control />
      </Form.Group>
      <Form.Group controlId="partType">
        <Form.Label>Type</Form.Label>
        <Form.Control />
      </Form.Group>
      <Form.Group controlId="Code">
        <Form.Label>Code</Form.Label>
        <Form.Control />
      </Form.Group>
      <Form.Group controlId="Manufacturer">
        <Form.Label>Manufacturer</Form.Label>
        <Form.Control />
      </Form.Group>
      <Form.Group controlId="vehicleType">
        <Form.Label>Vehicle Type</Form.Label>
        <Form.Control />
      </Form.Group>
      <Form.Group controlId="yearS">
        <Form.Label>Year(s)</Form.Label>
        <Form.Control />
      </Form.Group>
      <Form.Group controlId="price">
        <Form.Label>Price</Form.Label>
        <Form.Control type="number" min="0" step="0.01" />
      </Form.Group>
      <Form.Group controlId="stockLevel">
        <Form.Label>Stock level</Form.Label>
        <Form.Control type="number" min="0" />
      </Form.Group>
      <Form.Group controlId="stockLevelThreshold">
        <Form.Label>Stock level threshold</Form.Label>
        <Form.Control type="number" min="0" />
      </Form.Group>
    </Form>
  );
};

export default AddToInventoryForm;
