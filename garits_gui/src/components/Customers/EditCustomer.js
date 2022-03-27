import Form from "react-bootstrap/Form";
//import { SERVICES } from "../../dummy-data/services";
const EditCustomer = (props) => {
  const emailHandler = (ev) => {
    const newCustomer = props.customer;
    newCustomer.email = ev.target.value;
    props.setCustomer(newCustomer);
  };
  const nameHandler = (ev) => {
    const newCustomer = props.customer;
    newCustomer.name = ev.target.value;
    props.setCustomer(newCustomer);
  };
  const addressHandler = (ev) => {
    const newCustomer = props.customer;
    newCustomer.address = ev.target.value;
    props.setCustomer(newCustomer);
  };
  const postcodeHandler = (ev) => {
    const newCustomer = props.customer;
    newCustomer.postcode = ev.target.value;
    props.setCustomer(newCustomer);
  };
  const telephoneNumberHandler = (ev) => {
    const newCustomer = props.customer;
    newCustomer.telephoneNumber = ev.target.value;
    props.setCustomer(newCustomer);
  };
  const faxHandler = (ev) => {
    const newCustomer = props.customer;
    newCustomer.fax = ev.target.value;
    props.setCustomer(newCustomer);
  };
  const fixedDiscountHandler = (ev) => {
    const newCustomer = props.customer;
    newCustomer.fixedDiscount = ev.target.value;
    props.setCustomer(newCustomer);
  };

  return (
    <Form>
      <Form.Group>
        <Form.Label>Name</Form.Label>
        <Form.Control
          defaultValue={props.customer.name}
          onChange={emailHandler}
        />
      </Form.Group>
      <Form.Group>
        <Form.Label>Email</Form.Label>
        <Form.Control
          defaultValue={props.customer.email}
          onChange={nameHandler}
        />
      </Form.Group>
      <Form.Group>
        <Form.Label>Address</Form.Label>
        <Form.Control
          defaultValue={props.customer.address}
          onChange={addressHandler}
        />
      </Form.Group>
      <Form.Group>
        <Form.Label>Postcode</Form.Label>
        <Form.Control
          defaultValue={props.customer.postcode}
          onChange={postcodeHandler}
        />
      </Form.Group>
      <Form.Group>
        <Form.Label>Telephone number</Form.Label>
        <Form.Control
          defaultValue={props.customer.telephoneNumber}
          onChange={telephoneNumberHandler}
        />
      </Form.Group>
      <Form.Group>
        <Form.Label>FAX</Form.Label>
        <Form.Control defaultValue={props.customer.fax} onChange={faxHandler} />
      </Form.Group>
      <Form.Group>
        <Form.Label>Fixed discount</Form.Label>
        <Form.Control
          defaultValue={props.customer.fixedDiscount}
          onChange={fixedDiscountHandler}
          min={0}
          max={100}
        />
      </Form.Group>
    </Form>
  );
};

export default EditCustomer;
