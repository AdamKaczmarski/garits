import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
const NotificationModalPayments = (props) => {
  return (
    <Modal show={props.show} onHide={props.onClose} backdrop="static">
      <Modal.Header closeButton>
        <Modal.Title>Late Payments</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        These customers are late with their payments
        <ul>
            <li>Customer 1</li>
            <li>Customer 2</li>
        </ul>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="danger" onClick={props.onClose}>
          Close
        </Button>
        <Button variant="primary" onClick={props.onClose}>
          Acknowledge
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default NotificationModalPayments;
