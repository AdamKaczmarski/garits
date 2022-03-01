import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import UserChangeRoleForm from "./UserChangeRoleForm";
const UserChangeRoleModal = (props) => {
  return (
    <Modal
      size="lg"
      show={props.show}
      onHide={props.onClose}
      backdrop="static"
    >
      <Modal.Header closeButton>
        <Modal.Title>Change role</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <UserChangeRoleForm roleFrom={props.roleFrom}/>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="danger" onClick={props.onClose}>
          Close
        </Button>
        <Button variant="primary" onClick={props.onClose}>Change</Button>
      </Modal.Footer>
    </Modal>
  );
};

export default UserChangeRoleModal;
