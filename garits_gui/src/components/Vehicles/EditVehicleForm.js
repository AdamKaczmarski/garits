import Form from "react-bootstrap/Form";

const EditVehicleForm = (props) => {
  const regNoHandler = (ev) => {props.vehicle.idRegNo = ev.target.value;};
  const manufacturerHandler = (ev) => {props.vehicle.manufacturer = ev.target.value;};
  const modelHandler = (ev) => {props.vehicle.model = ev.target.value;};
  const chassisNoHandler = (ev) => {props.vehicle.chassisNumber = ev.target.value;};
  const engineNoHandler = (ev) => {props.vehicle.engineSerialNumber = ev.target.value;};
  const colourHandler = (ev) => {props.vehicle.colour = ev.target.value;};
  const motHandler = (ev) => {props.vehicle.lastMot = ev.target.value;};
  return (
    <Form className="mt-3">
      <Form.Group controlId="reg_no">
        <Form.Label>Registration Number</Form.Label>
        <Form.Control onChange={regNoHandler} defaultValue={props.vehicle.idRegNo}/>
      </Form.Group>
      <Form.Group controlId="manufacturer">
        <Form.Label>Manufacturer</Form.Label>
        <Form.Control onChange={manufacturerHandler} defaultValue={props.vehicle.manufacturer}/>
      </Form.Group>
      <Form.Group controlId="model">
        <Form.Label>Model</Form.Label>
        <Form.Control onChange={modelHandler} defaultValue={props.vehicle.model}/>
      </Form.Group>
      <Form.Group controlId="engine_no">
        <Form.Label>Engine Serial Number</Form.Label>
        <Form.Control onChange={engineNoHandler} defaultValue={props.vehicle.engineSerialNumber}/>
      </Form.Group>
      <Form.Group controlId="chassis_no">
        <Form.Label>Chassis Number</Form.Label>
        <Form.Control onChange={chassisNoHandler} defaultValue={props.vehicle.chassisNumber}/>
      </Form.Group>
      <Form.Group controlId="colour">
        <Form.Label>Colour</Form.Label>
        <Form.Control onChange={colourHandler} defaultValue={props.vehicle.colour}/>
      </Form.Group>
      <Form.Group controlId="mot">
        <Form.Label>Last MOT</Form.Label>
        <Form.Control type="date" onChange={motHandler} defaultValue={props.vehicle.lastMot}/>
      </Form.Group>
    </Form>
  );
};

export default EditVehicleForm;
