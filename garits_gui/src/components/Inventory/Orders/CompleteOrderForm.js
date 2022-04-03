import Form from "react-bootstrap/Form";

const CompleteOrderForm = (props) => {
  const arrivalDateHandler = (e) => {
    props.newArrivalOrder.arrivalDate = e.target.value;
  };

  return (
    <Form>
      <Form.Group controlId="arrivalDate">
        <Form.Control
          type="date"
          onChange={arrivalDateHandler}
          defaultValue={new Date().toISOString().substring(0, 10)}
        />
      </Form.Group>
    </Form>
  );
};

export default CompleteOrderForm;
