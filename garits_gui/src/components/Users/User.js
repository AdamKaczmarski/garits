import Dropdown from "react-bootstrap/Dropdown";

const User = (props) => {
  const role =
    props.user.role.charAt(0).toUpperCase() +
    props.user.role.slice(1).toLowerCase();
  return (
    <tr>
      <td>{props.user.id}</td>
      <td>{props.user.first_name + " " + props.user.last_name}</td>
      <td>{props.user.email}</td>
      <td>{role}</td>
      <td>
        <Dropdown className="m-0 p-0" align={"end"}>
          <Dropdown.Toggle variant="secondary">Action</Dropdown.Toggle>

          <Dropdown.Menu>
            <Dropdown.Item href="#/action-1">Reset password</Dropdown.Item>
            <Dropdown.Item href="#/action-2">Change role</Dropdown.Item>
            {props.user.role === "ADMIN" ? null : (
              <Dropdown.Item href="#/action-3">Delete</Dropdown.Item>
            )}
          </Dropdown.Menu>
        </Dropdown>
      </td>
    </tr>
  );
};

export default User;
