import Form from "react-bootstrap/Form";
const roles = ["MECHANIC", "FRANCHISEE", "RECEPTIONIST", "FOREPERSON"];
const UserChangeRoleForm = (props) => {
  const options = roles.map((role, index) => {
    if (props.roleFrom !== role) {
      const roleLC = role.charAt(0).toUpperCase() + role.slice(1).toLowerCase();
      return (
        <option key={index} value={role}>
          {roleLC}
        </option>
      );
    } else return null;
  });
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
        <Form.Select aria-label="roleSelect">
          <option>Role...</option>
          {options}
        </Form.Select>
      </Form.Group>
    </Form>
  );
};

export default UserChangeRoleForm;
