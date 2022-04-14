import { useNavigate } from "react-router-dom";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
const NotificationModalPayments = (props) => {
  const navigate = useNavigate();
  const regularCustomers = props.latePaymentCustomers.map((customer, index) => {
    if (customer.accountHolder == false || !customer.accountHolder) {
      return (
        <li key={index}>
          {customer.name} - ID: {customer.idCustomer}
        </li>
      );
    }
  });
  const accHolders = props.latePaymentCustomers.map((customer, index) => {
    if (customer.accountHolder == true) {
      return (
        <li key={index}>
          {customer.name} - ID: {customer.idCustomer}
        </li>
      );
    }
  });
  return (
    <Modal show={props.show} onHide={props.onClose} backdrop="static">
      <Modal.Header closeButton>
        <Modal.Title>Late Payments</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        These customers are late with their payments
        <ul>
          {regularCustomers}
        </ul>
        These account holders are late with their payments
        <ul>
          {accHolders}
        </ul>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="danger" onClick={props.onClose}>
          Close
        </Button>
        <Button variant="primary" onClick={props.onClose}>
          Acknowledge
        </Button>
        <Button
          variant="primary"
          onClick={() => {
            navigate("/payments");
            props.onClose();
          }}
        >
          Go to payments
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default NotificationModalPayments;
