import { useState } from "react";
import Dropdown from "react-bootstrap/Dropdown";
import UserChangeRoleModal from "./UserChangeRoleModal";
import axios from "axios";
const User = (props) => {
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);

  const role =
    props.user.roles[0].roleName.charAt(0).toUpperCase() +
    props.user.roles[0].roleName.slice(1).toLowerCase();

  const deleteUser = () => {
    try {
      const response = axios({
        method:"DELETE",
        url:"http://localhost:8080/users/"+props.user.idUser
      });
      console.log(response)
    } catch (err) {
        console.log(err)
    } finally {
      window.location.reload();
    }
  }

  return (
    <>
      <tr>
        <td>{props.user.idUser}</td>
        <td>{props.user.firstName + " " + props.user.lastName}</td>
        <td>{props.user.email}</td>
        <td>{role}</td>
        <td>
          <Dropdown className="m-0 p-0" align={"end"}>
            <Dropdown.Toggle variant="secondary">Action</Dropdown.Toggle>

            <Dropdown.Menu>
              <Dropdown.Item>
                Edit
              </Dropdown.Item>
              <Dropdown.Item href="#/action-1">Reset password</Dropdown.Item>
              {props.user.role === "ADMIN" ? null : (
                <Dropdown.Item onClick={handleShow}>Change role</Dropdown.Item>
              )}

              {props.user.role === "ADMIN" ? null : (
                <Dropdown.Item style={{ backgroundColor: "rgba(242, 97, 99,0.2)" }} onClick={deleteUser}>Delete</Dropdown.Item>
              )}
            </Dropdown.Menu>
          </Dropdown>
        </td>
      </tr>
      <UserChangeRoleModal
        show={show}
        onClose={handleShow}
        roleFrom={props.user.roles[0].roleName}
        idUser={props.user.idUser}
      />
    </>
  );
};

export default User;
