import Form from "react-bootstrap/Form";

const AddServiceForm = (props) => {
  const priceHandler = (ev) => {
    props.service.servicePrice = ev.target.value;
  };
  const nameHandler = (ev) => {
    props.service.serviceName = ev.target.value;
  };
  const descriptionHandler = (ev) => {
    props.service.shortDescription = ev.target.value;
  };
  const timeHandler = (ev) => {
    props.service.approxTimeMin = ev.target.value;
  };
  return (
    <Form className="mt-3">
      <Form.Group controlId="name">
        <Form.Label>Service name</Form.Label>
        <Form.Control onChange={nameHandler} />
      </Form.Group>
      <Form.Group controlId="price">
        <Form.Label>Service price</Form.Label>
        <Form.Control
          type="number"
          step="0.01"
          min="0"
          onChange={priceHandler}
        />
      </Form.Group>
      <Form.Group controlId="desc">
        <Form.Label>Short description</Form.Label>
        <Form.Control as="textarea" rows={3} onChange={descriptionHandler} />
      </Form.Group>
      <Form.Group controlId="time">
        <Form.Label>Approx. time</Form.Label>
        <Form.Control type="number" min="0" onChange={timeHandler} />
      </Form.Group>
    </Form>
  );
};

export default AddServiceForm;
