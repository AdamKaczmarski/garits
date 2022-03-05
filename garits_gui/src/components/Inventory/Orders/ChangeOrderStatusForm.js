import Form from "react-bootstrap/Form";

const STATUSES = ["ordered", "completed","cancelled"];

const ChangeOrderStatusForm = (props) => {
  const options = STATUSES.filter(status=>status!==props.status).map((status, index) => (
    <option value={status} key={index}>
      {status.charAt(0).toUpperCase() +
            status.slice(1).toLowerCase()}
    </option>
  ));
  return (
    <Form>
      <Form.Group controlId="changeStatus">
        <Form.Select>{options}</Form.Select>
      </Form.Group>
    </Form>
  );
};

export default ChangeOrderStatusForm;
