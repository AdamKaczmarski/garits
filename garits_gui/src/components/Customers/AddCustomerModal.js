import Modal from "react-bootstrap/Modal";
import AddCustomerForm from "./AddCustomerForm";
import Button from "react-bootstrap/Button";
const AddUserModal = (props) => {
  return (
    <Modal
      size="lg"
      show={props.show}
      onHide={props.onClose}
      backdrop="static"
    >
      <Modal.Header closeButton>
        <Modal.Title>Add customer</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <AddCustomerForm />
      </Modal.Body>
      <Modal.Footer>
        <Button variant="danger" onClick={props.onClose}>
          Close
        </Button>
        <Button variant="primary" onClick={props.onClose}>
          Add
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default AddUserModal;
