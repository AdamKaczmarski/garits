import Form from "react-bootstrap/Form";

const EditVehicleForm = (props) => {
  const regNoHandler = (ev) => {
    props.editedVehicle.idRegNo = ev.target.value;
  };
  const manufacturerHandler = (ev) => {
    props.editedVehicle.manufacturer = ev.target.value;
  };
  const modelHandler = (ev) => {
    props.editedVehicle.model = ev.target.value;
  };
  const engineSerialNoHandler = (ev) => {
    props.editedVehicle.engineSerialNumber = ev.target.value;
  };
  const chassisNoHandler = (ev) => {
    props.editedVehicle.chassisNumber = ev.target.value;
  };
  const colourHandler = (ev) => {
    props.editedVehicle.colour = ev.target.value;
  };
  const motHandler = (ev) => {
    props.editedVehicle.lastMot = ev.target.value;
  };
  return (
    <Form className="mt-3">
      <Form.Group controlId="reg_no">
        <Form.Label>Registration Number</Form.Label>
        <Form.Control
          onChange={regNoHandler}
          defaultValue={props.editedVehicle.idRegNo}
        />
      </Form.Group>
      <Form.Group controlId="manufacturer">
        <Form.Label>Manufacturer</Form.Label>
        <Form.Control
          onChange={manufacturerHandler}
          defaultValue={props.editedVehicle.manufacturer}
        />
      </Form.Group>
      <Form.Group controlId="model">
        <Form.Label>Model</Form.Label>
        <Form.Control
          onChange={modelHandler}
          defaultValue={props.editedVehicle.model}
        />
      </Form.Group>
      <Form.Group controlId="engine_no">
        <Form.Label>Engine Serial Number</Form.Label>
        <Form.Control
          onChange={engineSerialNoHandler}
          defaultValue={props.editedVehicle.engineSerialNumber}
        />
      </Form.Group>
      <Form.Group controlId="chassis_no">
        <Form.Label>Chassis Number</Form.Label>
        <Form.Control
          onChange={chassisNoHandler}
          defaultValue={props.editedVehicle.chassisNumber}
        />
      </Form.Group>
      <Form.Group controlId="colour">
        <Form.Label>Colour</Form.Label>
        <Form.Control
          onChange={colourHandler}
          defaultValue={props.editedVehicle.colour}
        />
      </Form.Group>
      <Form.Group controlId="mot">
        <Form.Label>Last MOT</Form.Label>
        <Form.Control
          type="date"
          onChange={motHandler}
          defaultValue={props.editedVehicle.lastMot}
        />
      </Form.Group>
    </Form>
  );
};

export default EditVehicleForm;
