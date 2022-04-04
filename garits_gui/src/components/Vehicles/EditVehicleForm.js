import Form from "react-bootstrap/Form";

const EditVehicleForm = (props) => {
  /*
    need to pass customer props here
    */
  return (
    <Form className="mt-3">
      <Form.Group controlId="reg_no">
        <Form.Label>Registration Number</Form.Label>
        <Form.Control />
      </Form.Group>
      <Form.Group controlId="manufacturer">
        <Form.Label>Manufacturer</Form.Label>
        <Form.Control />
      </Form.Group>
      <Form.Group controlId="model">
        <Form.Label>Model</Form.Label>
        <Form.Control />
      </Form.Group>
      <Form.Group controlId="engine_no">
        <Form.Label>Engine Serial Number</Form.Label>
        <Form.Control />
      </Form.Group>
      <Form.Group controlId="chassis_no">
        <Form.Label>Chassis Number</Form.Label>
        <Form.Control />
      </Form.Group>
      <Form.Group controlId="colour">
        <Form.Label>Colour</Form.Label>
        <Form.Control />
      </Form.Group>
      <Form.Group controlId="mot">
        <Form.Label>Last MOT</Form.Label>
        <Form.Control type="date" />
      </Form.Group>
    </Form>
  );
};

export default EditVehicleForm;
