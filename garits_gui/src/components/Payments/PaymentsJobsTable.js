import { useState } from "react";
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";
import PaymentJob from "./PaymentJob";

import { PAYMENTS_JOBS } from "../../dummy-data/payments";
import PaymentModal from "./PaymentModal";
import AddJobPaymentForm from './AddJobPaymentForm';
const PaymentsTable = () => {
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);
  const paymentsJobs = PAYMENTS_JOBS.map(payment=><PaymentJob key={payment.id} payment={payment}/>);
  return (
    <>
      <Table striped hover className="mt-3">
        <thead>
          <tr>
            <th>ID</th>
            <th>Customer</th>
            <th>Payment Type</th>
            <th>Amount</th>
            <th>Job ID</th>
            <th>Payment Issued</th>
            <th>Payment Date</th>
            <th>Payment Due</th>
            <th
            >
              <span>Actions</span></th><th>
              <Button variant="outline-primary" onClick={handleShow}>
                +
              </Button>
            </th>
          </tr>
        </thead>
        <tbody>{paymentsJobs}</tbody>
      </Table>
      <PaymentModal show={show} onClose={handleShow} title="Add job payment" form={<AddJobPaymentForm />} />
    </>
  );
};

export default PaymentsTable;
