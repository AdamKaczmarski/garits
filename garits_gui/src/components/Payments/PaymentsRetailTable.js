import { useState } from "react";
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";
import PaymentRetail from "./PaymentRetail";
import AddPaymentModal from './AddPaymentModal'
import AddJobPaymentForm from './AddJobPaymentForm'
import { PAYMENTS_RETAIL } from "../../dummy-data/payments";
const PaymentsTable = () => {
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);
  const paymentsRetail = PAYMENTS_RETAIL.map(payment=><PaymentRetail key={payment.id} payment={payment}/>);
  return (
    <>
      <Table striped hover className="mt-3">
        <thead>
          <tr>
            <th>ID</th>
            <th>Payment Type</th>
            <th>Amount</th>
            <th>Job ID</th>
            <th>Payment Date</th>
            <th>Payment Due</th>
            <th
              style={{ display: "flex" }}
              className="justify-content-between  "
            >
              Action{" "}
              <Button variant="outline-primary" onClick={handleShow}>
                +
              </Button>
            </th>
          </tr>
        </thead>
        <tbody>{paymentsRetail}</tbody>
      </Table>
      <AddPaymentModal form={<AddJobPaymentForm />}/>
    </>
  );
};

export default PaymentsTable;
