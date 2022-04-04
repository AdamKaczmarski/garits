import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
const AddVehicleModal = (props) => {
  return (
    <Modal size="lg" show={props.show} onHide={props.onClose} backdrop="static">
      <Modal.Header closeButton>
        <Modal.Title>{props.title}</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        {props.form}
      </Modal.Body>
      <Modal.Footer>
        <Button variant="danger" onClick={props.onClose}>
          Close
        </Button>
        <Button variant="primary" onClick={props.submitAction}>
          Add
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default AddVehicleModal;
