import Form from "react-bootstrap/Form";

const AddToInventoryForm = (props) => {
  const codeHandler = (ev) => {
    props.newPart.code = ev.target.value;
  };
  const partNameHandler = (ev) => {
    props.newPart.partName = ev.target.value;
  };
  const partTypeHandler = (ev) => {
    props.newPart.partType = ev.target.value;
  };
  const manufacturerHandler = (ev) => {
    props.newPart.manufacturer = ev.target.value;
  };
  const vehicleTypeHandler = (ev) => {
    props.newPart.vehicleType = ev.target.value;
  };
  const yearsHandler = (ev) => {
    props.newPart.yearS = ev.target.value;
  };
  const priceHandler = (ev) => {
    props.newPart.price = ev.target.value;
  };
  const stockLevelHandler = (ev) => {
    props.newPart.stockLevel = ev.target.value;
  };
  const stockLevelThresholdHandler = (ev) => {
    props.newPart.stockLevelThreshold = ev.target.value;
  };

  return (
    <Form>
      <Form.Group controlId="partName">
        <Form.Label>Name</Form.Label>
        <Form.Control onChange={partNameHandler} />
      </Form.Group>
      <Form.Group controlId="partType">
        <Form.Label>Type</Form.Label>
        <Form.Control onChange={partTypeHandler} />
      </Form.Group>
      <Form.Group controlId="Code">
        <Form.Label>Code</Form.Label>
        <Form.Control onChange={codeHandler} />
      </Form.Group>
      <Form.Group controlId="Manufacturer">
        <Form.Label>Manufacturer</Form.Label>
        <Form.Control onChange={manufacturerHandler} />
      </Form.Group>
      <Form.Group controlId="vehicleType">
        <Form.Label>Vehicle Type</Form.Label>
        <Form.Control onChange={vehicleTypeHandler} />
      </Form.Group>
      <Form.Group controlId="yearS">
        <Form.Label>Year(s)</Form.Label>
        <Form.Control onChange={yearsHandler} />
      </Form.Group>
      <Form.Group controlId="price">
        <Form.Label>Price</Form.Label>
        <Form.Control
          onChange={priceHandler}
          type="number"
          min="0"
          step="0.01"
        />
      </Form.Group>
      <Form.Group controlId="stockLevel">
        <Form.Label>Stock level</Form.Label>
        <Form.Control onChange={stockLevelHandler} type="number" min="0" />
      </Form.Group>
      <Form.Group controlId="stockLevelThreshold">
        <Form.Label>Stock level threshold</Form.Label>
        <Form.Control
          onChange={stockLevelThresholdHandler}
          type="number"
          min="0"
        />
      </Form.Group>
    </Form>
  );
};

export default AddToInventoryForm;
