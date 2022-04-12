import Modal from "react-bootstrap/Modal";
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";
import PartModal from "./PartModal";
import styles from "./NotificationModalStock.module.css";
import { useNavigate } from "react-router-dom";
const NotificationModalStock = (props) => {
  const navigate = useNavigate();
  const parts = props.lowStockParts.map((part) => (
    <PartModal key={part.idPart} part={part} />
  ));

  return (
    <Modal
      dialogClassName={styles.modal70w}
      show={props.show}
      onHide={props.onClose}
      backdrop="static"
    >
      <Modal.Header closeButton>
        <Modal.Title>Low Stock!</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Table striped hover className="mt-3">
          <thead>
            <tr>
              <th>Code</th>
              <th>Name</th>
              <th>Type</th>
              <th>Manufacturer</th>
              <th>Vehicle type</th>
              <th>Year(s)</th>
              <th>Stock level</th>
              <th>Stock level threshold</th>
            </tr>
          </thead>
          <tbody>{parts}</tbody>
        </Table>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="danger" onClick={props.onClose}>
          Close
        </Button>
        <Button variant="secondary" onClick={props.onClose}>
          Okay
        </Button>
        <Button variant="primary" onClick={()=>{navigate("/inventory");props.onClose();}}>
          Go to orders
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default NotificationModalStock;
