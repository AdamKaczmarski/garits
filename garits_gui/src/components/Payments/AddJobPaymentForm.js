import Form from "react-bootstrap/Form";

import { CUSTOMERS } from "../../dummy-data/customers";

const AddJobPaymentForm = (props) => {
  const customerNames = CUSTOMERS.map((customer) => (
    <option key={customer.id} value={customer.id}>
      {customer.name}
    </option>
  ));
  /*
    For payment due, use the payment_issued value and add a month to it
  */
  const getFirstDayOfNextMonth = () => {
    const date = new Date();

    return new Date(date.getFullYear(), date.getMonth() + 1, 1).toISOString().substring(0, 10);
  };
  return (
    <>
      <Form>
        <Form.Group controlId="customer">
          <Form.Label>Customer</Form.Label>
          <Form.Select>{customerNames}</Form.Select>
        </Form.Group>
        <Form.Group controlId="paymentType">
          <Form.Label>Payment Type</Form.Label>
          <Form.Select>
            <option value="card">Card</option>
            <option value="cash">Cash</option>
          </Form.Select>
        </Form.Group>
        <Form.Group controlId="amount">
          <Form.Label>Amount</Form.Label>
          <Form.Control type="number" min="0" step="0.01" />
        </Form.Group>
        <Form.Group controlId="jobID">
          <Form.Label>Job ID</Form.Label>
          <Form.Control type="number" min="0" />
        </Form.Group>
        <Form.Group controlId="pay_issued">
          <Form.Label>Payment Issued</Form.Label>
          <Form.Control
            type="date"
            defaultValue={new Date().toISOString().substring(0, 10)}
          />
        </Form.Group>
        <Form.Group controlId="pay_date">
          <Form.Label>Payment Date</Form.Label>
          <Form.Control
            type="date"
            defaultValue={new Date().toISOString().substring(0, 10)}
          />
        </Form.Group>
        <Form.Group controlId="pay_due">
          <Form.Label>Payment Due</Form.Label>
          <Form.Control type="date" defaultValue={getFirstDayOfNextMonth()} />
        </Form.Group>
      </Form>
    </>
  );
};

export default AddJobPaymentForm;
