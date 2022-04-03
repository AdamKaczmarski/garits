import { useState } from "react";
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";

import PaymentRetail from "./PaymentRetail";
import { PAYMENTS_RETAIL } from "../../dummy-data/payments";
import AddRetailPaymentForm from "./AddRetailPaymentForm";
import PaymentModal from "./PaymentModal";

const PaymentsRetailTable = () => {
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);
  const paymentsRetail = PAYMENTS_RETAIL.map((paymentRetail) => (
    <PaymentRetail key={paymentRetail.id_sale} paymentRetail={paymentRetail} />
  ));
  return (
    <>
      <Table striped hover className="mt-3">
        <thead>
          <tr>
            <th>Sale ID</th>
            <th>Sale Date</th>
            <th>Part Name</th>
            <th>Quantity</th>
            <th>Part Price</th>
            <th>Total amount</th>
            <th>
              <span>Actions</span>
            </th>
            <th>
              <Button variant="outline-primary" onClick={handleShow}>
                +
              </Button>
            </th>
          </tr>
        </thead>
        <tbody>{paymentsRetail}</tbody>
      </Table>
      <PaymentModal show={show} onClose={handleShow} title="Add payment" form={<AddRetailPaymentForm />} />
    </>
  );
};

export default PaymentsRetailTable;
