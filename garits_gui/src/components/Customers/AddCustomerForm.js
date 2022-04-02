import Form from "react-bootstrap/Form";

const AddUserForm = (props) => {
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
    <Form className="mt-3">
      <Form.Group controlId="formEmail">
        <Form.Label>Email address</Form.Label>
        <Form.Control type="email" onChange={emailHandler} />
      </Form.Group>
      <Form.Group controlId="name">
        <Form.Label>Name</Form.Label>
        <Form.Control onChange={nameHandler} />
      </Form.Group>
      <Form.Group controlId="address">
        <Form.Label>Address</Form.Label>
        <Form.Control onChange={addressHandler} />
      </Form.Group>
      <Form.Group controlId="postcode">
        <Form.Label>Postcode</Form.Label>
        <Form.Control onChange={postcodeHandler} />
      </Form.Group>
      <Form.Group controlId="tel_no">
        <Form.Label>Telephone Number</Form.Label>
        <Form.Control onChange={telephoneNumberHandler} />
      </Form.Group>
      <Form.Group controlId="fax">
        <Form.Label>FAX</Form.Label>
        <Form.Control onChange={faxHandler} />
      </Form.Group>
      <Form.Group controlId="fixed_discount">
        <Form.Label>Fixed Discount</Form.Label>
        <Form.Control
          type="number"
          min={0}
          max={100}
          onChange={fixedDiscountHandler}
        />
      </Form.Group>
    </Form>
  );
};

export default AddUserForm;
