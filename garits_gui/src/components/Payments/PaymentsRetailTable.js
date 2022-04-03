import { useState, useEffect } from "react";
import axios from "axios";
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";

import PaymentRetail from "./PaymentRetail";
import { PAYMENTS_RETAIL } from "../../dummy-data/payments";
import AddRetailPaymentForm from "./AddRetailPaymentForm";
import PaymentModal from "./PaymentModal";
import { Spinner } from "react-bootstrap";

const PaymentsRetailTable = () => {
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);
  const [isLoading, setIsLoading] = useState(true);
  const [paymentsRetail, setPaymentsRetail] = useState([]);
  let paymentsRetailView;

  const obtainPaymentsRetail =async () => {
    try {
      const response = await axios({
        method: "GET",
        url: "http://localhost:8080/payments-retail",
      });
      console.log(response);
      if (response.status === 200) setPaymentsRetail(response.data);
    } catch (e) {
      console.log(e);
    } finally {
      setIsLoading(false);
    }
  };
  const deletePaymentRetail = async (idPayment) => {
    try {
      const response = await axios({
        method: "DELETE",
        url: `http://localhost:8080/payments-retail/${idPayment}`,
      });
      console.log(response);
    } catch (e) {
      console.log(e);
    } finally {
      obtainPaymentsRetail();
    }
  }
  useEffect(() => {
    obtainPaymentsRetail();
  }, []);

  if (isLoading) {
    return <Spinner variant="primary" />;
  }
  if (paymentsRetail && paymentsRetail.length>0) {
    console.log("MAPPING")
    paymentsRetailView = paymentsRetail.map((paymentRetail) => (
      <PaymentRetail key={paymentRetail.idPayment} paymentRetail={paymentRetail} deletePaymentRetail={deletePaymentRetail} />
    ));}
  
  return (
    <>
      <Table striped hover className="mt-3">
        <thead>
          <tr>
            <th>Sale ID</th>
            <th>Sale Date</th>
            <th>Customer</th>
            <th>Payment Type</th>
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
        <tbody>{paymentsRetailView}</tbody>
      </Table>
      <PaymentModal
        show={show}
        onClose={handleShow}
        title="Add payment"
        form={<AddRetailPaymentForm />}
      />
    </>
  );
};

export default PaymentsRetailTable;
