import Form from "react-bootstrap/Form";
const roles = ["ROLE_MECHANIC", "ROLE_FRANCHISEE", "ROLE_RECEPTIONIST", "ROLE_FOREPERSON"];
const UserChangeRoleForm = (props) => {
  const options = roles.map((role, index) => {
    if (props.roleFrom.toUpperCase() !== role.substring(5, role.length)) {
      let roleLC = role.substring(5, role.length);
      roleLC =
      roleLC.charAt(0).toUpperCase() +
      roleLC.slice(1).toLowerCase();
    
      return (
        <option key={index} value={role}>
          {roleLC}
        </option>
      );
    } else return null;
  });
  const roleHandler = ev => {
    props.setRoleName(ev.target.value);
  }
  return (
    <Form className="mt-3">
      <Form.Group>
        <Form.Label>
          From:{" "}
          {props.roleFrom.charAt(0).toUpperCase() +
            props.roleFrom.slice(1).toLowerCase()}
        </Form.Label>
      </Form.Group>
      <Form.Group controlId="role">
        <Form.Label>To: </Form.Label>
        <Form.Select aria-label="roleSelect" onChange={roleHandler}>
          <option>Role...</option>
          {options}
        </Form.Select>
      </Form.Group>
    </Form>
  );
};

export default UserChangeRoleForm;
