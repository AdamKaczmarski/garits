import Form from "react-bootstrap/Form";

const EditServiceForm = (props) => {
  return (
    <>
      <Form className="mt-3">
        <Form.Group controlId="name">
          <Form.Label>Service name</Form.Label>
          <Form.Control value={props.service.service_name} />
        </Form.Group>
        <Form.Group controlId="price">
          <Form.Label>Service price</Form.Label>
          <Form.Control
            type="number"
            step="0.01"
            min="0"
            value={props.service.service_price}
          />
        </Form.Group>
        <Form.Group controlId="time">
          <Form.Label>Approx. time</Form.Label>
          <Form.Control
            type="number"
            min="0"
            value={props.service.approx_time_min}
          />
        </Form.Group>
        <Form.Group controlId="desc">
          <Form.Label>Short description</Form.Label>
          <Form.Control
            as="textarea"
            rows={3}
            value={props.service.short_description}
          />
        </Form.Group>
      </Form>
    </>
  );
};

export default EditServiceForm;
