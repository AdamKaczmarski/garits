import {useState, useContext} from 'react';
import axios from 'axios';
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import UserChangeRoleForm from "./UserChangeRoleForm";
import AuthContext from '../../store/auth-context';
const UserChangeRoleModal = (props) => {
  const [roleName,setRoleName] = useState(props.roleFrom);
  const authCtx = useContext(AuthContext);
  const changeRole = ()=>{
    try {
      axios({
        method:"PATCH",
        url:"http://localhost:8080/users/"+props.idUser+"/role",
        data: {
          roleName
        },
        headers:{'Authorization': `Bearer ${authCtx.authData.token}`}
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
