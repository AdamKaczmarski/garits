import Form from "react-bootstrap/Form";

const EditPartForm = (props) => {
  const codeHandler = (ev) => {
    props.editedPart.code = ev.target.value;
  };
  const partNameHandler = (ev) => {
    props.editedPart.partName = ev.target.value;
  };
  const partTypeHandler = (ev) => {
    props.editedPart.partType = ev.target.value;
  };
  const manufacturerHandler = (ev) => {
    props.editedPart.manufacturer = ev.target.value;
  };
  const vehicleTypeHandler = (ev) => {
    props.editedPart.vehicleType = ev.target.value;
  };
  const yearsHandler = (ev) => {
    props.editedPart.yearS = ev.target.value;
  };
  const priceHandler = (ev) => {
    props.editedPart.price = ev.target.value;
  };
  const stockLevelHandler = (ev) => {
    props.editedPart.stockLevel = ev.target.value;
  };
  const stockLevelThresholdHandler = (ev) => {
    props.editedPart.stockLevelThreshold = ev.target.value;
  };
  return (
    <Form className="mt-3">
      <Form.Group controlId="code">
        <Form.Label>Code</Form.Label>
        <Form.Control defaultValue={props.part.code} onChange={codeHandler} />
      </Form.Group>
      <Form.Group controlId="name">
        <Form.Label>Name</Form.Label>
        <Form.Control
          defaultValue={props.part.partName}
          onChange={partNameHandler}
        />
      </Form.Group>
      <Form.Group controlId="type">
        <Form.Label>Type</Form.Label>
        <Form.Control
          defaultValue={props.part.partType}
          onChange={partTypeHandler}
        />
      </Form.Group>
      <Form.Group controlId="manufacturer">
        <Form.Label>Manufacturer</Form.Label>
        <Form.Control
          defaultValue={props.part.manufacturer}
          onChange={manufacturerHandler}
        />
      </Form.Group>
      <Form.Group controlId="vehicle_type">
        <Form.Label>Vehicle Type</Form.Label>
        <Form.Control
          defaultValue={props.part.vehicleType}
          onChange={vehicleTypeHandler}
        />
      </Form.Group>
      <Form.Group controlId="yerS">
        <Form.Label>Year(s)</Form.Label>
        <Form.Control defaultValue={props.part.yearS} onChange={yearsHandler} />
      </Form.Group>
      <Form.Group controlId="price">
        <Form.Label>Price</Form.Label>
        <Form.Control
          type="number"
          step="0.01"
          min="0"
          defaultValue={props.part.price}
          onChange={priceHandler}
        />
      </Form.Group>
      <Form.Group controlId="stockLevel">
        <Form.Label>Stock Level</Form.Label>
        <Form.Control
          type="number"
          min="0"
          defaultValue={props.part.stockLevel}
          onChange={stockLevelHandler}
        />
      </Form.Group>
      <Form.Group controlId="stockLevelThreshold">
        <Form.Label>Stock Level Threshold</Form.Label>
        <Form.Control
          type="number"
          min="0"
          defaultValue={props.part.stockLevelThreshold}
          onChange={stockLevelThresholdHandler}
        />
      </Form.Group>
    </Form>
  );
};

export default EditPartForm;
