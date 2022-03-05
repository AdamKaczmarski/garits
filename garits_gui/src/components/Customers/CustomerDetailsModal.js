import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import CustomerDetailsTabs from "./CustomerDetailsTabs";
import styles from './CustomerDetailsModal.module.css';
//import { useState } from "react";
const CustomerDetailsModal = (props) => {
/*   const [isReportsTab,setIsReportsTab]=useState(false);
 */  return (
    <Modal show={props.show} onHide={props.onClose}  dialogClassName={styles.modal90w} backdrop="static">
      <Modal.Header closeButton>
        <Modal.Title>{props.customer.name}</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <CustomerDetailsTabs customer={props.customer} /* setIsReportsTab={setIsReportsTab} *//>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="danger" onClick={props.onClose}>
          Close
        </Button>
        <Button variant="primary" onClick={props.onClose}>
          Save
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default CustomerDetailsModal;
