import { useState } from "react";
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";
import PaymentJob from "./PaymentJob";

import { PAYMENTS_JOBS } from "../../dummy-data/payments";
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
        <tbody>{paymentsJobs}</tbody>
      </Table>
    </>
  );
};

export default PaymentsTable;
