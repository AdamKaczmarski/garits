import Form from "react-bootstrap/Form";

const EditPartForm = (props) => {
  return (
    <Form className="mt-3">
      <Form.Group controlId="code">
        <Form.Label>Code</Form.Label>
        <Form.Control value={props.part.code} />
      </Form.Group>
      <Form.Group controlId="name">
        <Form.Label>Name</Form.Label>
        <Form.Control value={props.part.part_name} />
      </Form.Group>
      <Form.Group controlId="type">
        <Form.Label>Type</Form.Label>
        <Form.Control value={props.part.part_type} />
      </Form.Group>
      <Form.Group controlId="manufacturer">
        <Form.Label>Manufacturer</Form.Label>
        <Form.Control value={props.part.manufacturer} />
      </Form.Group>
      <Form.Group controlId="vehicle_type">
        <Form.Label>Vehicle Type</Form.Label>
        <Form.Control value={props.part.vehicle_type} />
      </Form.Group>
      <Form.Group controlId="yerS">
        <Form.Label>Year(s)</Form.Label>
        <Form.Control value={props.part.year_s} />
      </Form.Group>
      <Form.Group controlId="price">
        <Form.Label>Price</Form.Label>
        <Form.Control
          type="number"
          step="0.01"
          min="0"
          value={props.part.price}
        />
      </Form.Group>
      <Form.Group controlId="stockLevel">
        <Form.Label>Stock Level</Form.Label>
        <Form.Control type="number" min="0" value={props.part.stock_level} />
      </Form.Group>
      <Form.Group controlId="stockLevelThreshold">
        <Form.Label>Stock Level Threshold</Form.Label>
        <Form.Control
          type="number"
          min="0"
          value={props.part.stock_level_threshold}
        />
      </Form.Group>
    </Form>
  );
};

export default EditPartForm;
