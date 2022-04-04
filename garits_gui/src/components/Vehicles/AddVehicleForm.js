import Form from "react-bootstrap/Form";

const AddVehicleForm = (props) => {
  const regNoHandler = (ev) => {
    props.newVehicle.idRegNo = ev.target.value;
  };
  const manufacturerHandler = (ev) => {
    props.newVehicle.manufacturer = ev.target.value;
  };
  const modelHandler = (ev) => {
    props.newVehicle.model = ev.target.value;
  };
  const engineSerialNoHandler = (ev) => {
    props.newVehicle.engineSerialNumber = ev.target.value;
  };
  const chassisNoHandler = (ev) => {
    props.newVehicle.chassisNumber = ev.target.value;
  };
  const colourHandler = (ev) => {
    props.newVehicle.colour = ev.target.value;
  };
  const motHandler = (ev) => {
    props.newVehicle.lastMot = ev.target.value;
  };
  return (
    <Form className="mt-3">
      <Form.Group controlId="reg_no">
        <Form.Label>Registration Number</Form.Label>
        <Form.Control onChange={regNoHandler} />
      </Form.Group>
      <Form.Group controlId="manufacturer">
        <Form.Label>Manufacturer</Form.Label>
        <Form.Control onChange={manufacturerHandler}/>
      </Form.Group>
      <Form.Group controlId="model">
        <Form.Label>Model</Form.Label>
        <Form.Control onChange={modelHandler}/>
      </Form.Group>
      <Form.Group controlId="engine_no">
        <Form.Label>Engine Serial Number</Form.Label>
        <Form.Control onChange={engineSerialNoHandler}/>
      </Form.Group>
      <Form.Group controlId="chassis_no">
        <Form.Label>Chassis Number</Form.Label>
        <Form.Control onChange={chassisNoHandler}/>
      </Form.Group>
      <Form.Group controlId="colour">
        <Form.Label>Colour</Form.Label>
        <Form.Control onChange={colourHandler}/>
      </Form.Group>
      <Form.Group controlId="mot">
        <Form.Label>Last MOT</Form.Label>
        <Form.Control type="date" onChange={motHandler}/>
      </Form.Group>
    </Form>
  );
};

export default AddVehicleForm;
