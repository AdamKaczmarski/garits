import { ModalBody, ModalFooter, ModalTitle } from "react-bootstrap";
import Modal from "react-bootstrap/Modal";
import AddUserForm from "./AddUserForm";

const AddUserModal = () => {
  return (
    <Modal>
      <ModalTitle>Add user</ModalTitle>
      <ModalBody>
        <AddUserForm />
      </ModalBody>
      <ModalFooter>
        <Button variant="primary">Add</Button>
      </ModalFooter>
    </Modal>
  );
};

export default AddUserModal;
