import { useState, useEffect } from "react";
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";
import PaymentJob from "./PaymentJob";
import axios from "axios";
//import { PAYMENTS_JOBS } from "../../dummy-data/payments";
import PaymentModal from "../PaymentModal";
import AddJobPaymentForm from './AddJobPaymentForm';
import  Spinner  from "react-bootstrap/Spinner";

const PaymentsTable = () => {
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);
  const [isLoading, setIsLoading] = useState(true);
  const [paymentsJob, setPaymentsJobs] = useState([]);
  const obtainPaymentsJobs = async () => {
    try {
      const response = await axios({
        method: "GET",
        url: "http://localhost:8080/payments-jobs",
      });
      console.log(response);
      if (response.status === 200) setPaymentsJobs(response.data);
    } catch (e) {
      console.log(e);
    } finally {
      setIsLoading(false);
    }
  };
  const deletePaymentJob = async (idPayment) => {
    try {
      const response = await axios({
        method: "DELETE",
        url: `http://localhost:8080/payments-jobs/${idPayment}`,
      });
      console.log(response);
    } catch (e) {
      console.log(e);
    } finally {
      obtainPaymentsJobs();
    }
  };
  useEffect(() => {
    obtainPaymentsJobs();
  }, []);

  if (isLoading) {
    return <Spinner variant="primary" />;
  }
  let paymentsJobsView;
  if (paymentsJob && paymentsJob.length > 0) {
    paymentsJobsView = paymentsJob.map((paymentJob) => (
      <PaymentJob
        key={paymentJob.idPayment}
        paymentRetail={paymentJob}
        deletePaymentRetail={deletePaymentJob}
      />
    ));
  }


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
        <tbody>{paymentsJobsView}</tbody>
      </Table>
      <PaymentModal show={show} onClose={handleShow} title="Add job payment" form={<AddJobPaymentForm />} />
    </>
  );
};

export default PaymentsTable;
