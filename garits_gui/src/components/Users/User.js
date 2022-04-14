import { useState, useContext } from "react";
import Dropdown from "react-bootstrap/Dropdown";
import UserChangeRoleModal from "./UserChangeRoleModal";
import axios from "axios";
import AuthContext from "../../store/auth-context";
const User = (props) => {
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);
  const authCtx = useContext(AuthContext);
  let role = props.user.roles[0].roleName.substring(5, props.user.roles[0].length);
  role =
    role.charAt(0).toUpperCase() +
    role.slice(1).toLowerCase();

  const deleteUser = async() => {
    try {
      const response = await axios({
        method: "DELETE",
        url: "http://localhost:8080/users/" + props.user.idUser,
        headers:{'Authorization': `Bearer ${authCtx.authData.token}`}

      });
      console.log(response);
    } catch (err) {
      console.log(err);
    } finally {
      props.obtainUsers()
    }
  };
const resetPassword =async()=>{
  try {
    const response = await axios({
      method: "PATCH",
      url: "http://localhost:8080/users/" + props.user.idUser + "/password",
      headers:{'Authorization': `Bearer ${authCtx.authData.token}`}

    });
    console.log(response);
  } catch (err) {
    console.log(err);
  } finally {
    props.obtainUsers()
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
              <Dropdown.Item>Edit</Dropdown.Item>
              <Dropdown.Item onClick={resetPassword}>Reset password</Dropdown.Item>
              {props.user.role === "ADMIN" ? null : (
                <Dropdown.Item onClick={handleShow}>Change role</Dropdown.Item>
              )}

              {props.user.role === "ADMIN" ? null : (
                <Dropdown.Item
                  style={{ backgroundColor: "rgba(242, 97, 99,0.2)" }}
                  onClick={deleteUser}
                >
                  Delete
                </Dropdown.Item>
              )}
            </Dropdown.Menu>
          </Dropdown>
        </td>
      </tr>
      <UserChangeRoleModal
        show={show}
        onClose={handleShow}
        roleFrom={role}
        idUser={props.user.idUser}
      />
    </>
  );
};

export default User;
