import { useState, useContext } from "react";
import Modal from "react-bootstrap/Modal";
import AddUserForm from "./AddUserForm";
import Button from "react-bootstrap/Button";
import axios from "axios";
import AuthContext from '../../store/auth-context';
class User {
  constructor() {
    this.email = "";
    this.firstName = "";
    this.lastName = "";
    this.roles = [];
  }
}
const AddUserModal = (props) => {
  //const [lgShow, setLgShow] = useState(false);
  const [user, setUser] = useState(new User());
  const authCtx = useContext(AuthContext);
  const addUser = () => {
    console.log(user);
    try {
      const result = axios({
        method: "POST",
        url: "http://localhost:8080/users",
        data: user,
        headers:{'Authorization': `Bearer ${authCtx.authData.token}`}

      });
      console.log(result);
    } catch (err) {
      console.log(err);
    } finally {
      // I don't like this method to refresh the list of users but for this project's needs I think it will be fine as there's much more work to do.
      //window.location.reload();
      props.obtainUsers();
      props.onClose();
    }
  };
  return (
    <Modal size="lg" show={props.show} onHide={props.onClose} backdrop="static">
      <Modal.Header closeButton>
        <Modal.Title>Add user</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <AddUserForm user={user} setUser={setUser} />
      </Modal.Body>
      <Modal.Footer>
        <Button
          variant="danger"
          onClick={() => {
            setUser(new User());  
            props.onClose();
          }}
        >
          Close
        </Button>
        <Button variant="primary" onClick={addUser}>
          Add
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default AddUserModal;
