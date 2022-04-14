import Form from "react-bootstrap/Form";

const ChangeAccountStatus = (props) => {
   console.log(props.accountHolder)
  return (
    <Form>
      <Form.Group controlId="bool">
        <Form.Label>Is account holder?</Form.Label>
        <Form.Select
          onChange={(ev) => {
            props.decision = ev.target.value;
          }}
        >
          {props.accountHolder ? (
            <option value="false">False</option>
          ) : (
            <option value="true">True</option>
          )}
        </Form.Select>
      </Form.Group>
    </Form>
  );
};

export default ChangeAccountStatus;
