import {useState} from 'react';
import axios from 'axios';
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import UserChangeRoleForm from "./UserChangeRoleForm";
const UserChangeRoleModal = (props) => {
  const [roleName,setRoleName] = useState(props.roleFrom);
  const changeRole = ()=>{
    try {
      axios({
        method:"PATCH",
        url:"http://localhost:8080/users/"+props.idUser+"/role",
        data: {
          roleName
        }
      });
    } catch (err) {
      console.log(err)
    } finally {
      window.location.reload();
    }
  }
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
        <UserChangeRoleForm roleFrom={props.roleFrom} setRoleName={setRoleName}/>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="danger" onClick={props.onClose}>
          Close
        </Button>
        <Button variant="primary" onClick={changeRole}>Change</Button>
      </Modal.Footer>
    </Modal>
  );
};

export default UserChangeRoleModal;
