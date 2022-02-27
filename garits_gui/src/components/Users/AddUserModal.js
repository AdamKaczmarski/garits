import Modal from "react-bootstrap/Modal";
import AddUserForm from "./AddUserForm";
import Button from "react-bootstrap/Button";
const AddUserModal = (props) => {
  //const [lgShow, setLgShow] = useState(false);
  return (
    <Modal
      size="lg"
      show={props.show}
      onHide={props.onClose}
      aria-labelledby="example-modal-sizes-title-lg"
    >
      <Modal.Header closeButton>
        <Modal.Title>Add user</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <AddUserForm />
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={props.onClose}>
          Close
        </Button>
        <Button variant="primary" onClick={props.onClose}>Add</Button>
      </Modal.Footer>
    </Modal>
  );
};

export default AddUserModal;
