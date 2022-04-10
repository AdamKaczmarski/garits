import Form from "react-bootstrap/Form";
import {useState} from 'react';
const AddUserForm = (props) => {
  const [role,setRole]=useState("");
  const emailHandler = (ev) => {
    const newUser = {...props.user};
    newUser.email=ev.target.value;
    props.setUser(newUser);
  };
  const firstNameHandler = (ev) => {
    const newUser = {...props.user};
    newUser.firstName=ev.target.value;
    props.setUser(newUser);

  };
  const lastNameHandler = (ev) => {
    const newUser = {...props.user};
    newUser.lastName=ev.target.value;
    props.setUser(newUser);

  };
  const roleNameHandler = (ev) => {
    const newUser = {...props.user};
    newUser.roles.push({roleName:ev.target.value})
    props.setUser(newUser);
    setRole(ev.target.value);
  };
  const hourlyRateHandler=ev=>{
    const newUser = {...props.user};
    newUser.hourlyRate=ev.target.value;
    props.setUser(newUser);

  }
    return (
    <Form className="mt-3">
      <Form.Group controlId="formEmail">
        <Form.Label>Email address</Form.Label>
        <Form.Control type="email" onChange={emailHandler} />
      </Form.Group>
      <Form.Group controlId="firstName">
        <Form.Label>First Name</Form.Label>
        <Form.Control onChange={firstNameHandler} />
      </Form.Group>
      <Form.Group controlId="lastName">
        <Form.Label>Last Name</Form.Label>
        <Form.Control onChange={lastNameHandler} />
      </Form.Group>
      <Form.Group controlId="role">
        <Form.Label>Role</Form.Label>
        <Form.Select aria-label="roleSelect" onChange={roleNameHandler}>
          <option>Role...</option>
          <option value="MECHANIC">Mechanic</option>
          <option value="FRANCHISEE">Franchisee</option>
          <option value="MECHANIC">Mechanic</option>
          <option value="FOREPERSON">Foreperson</option>
        </Form.Select>
      </Form.Group>
      {role==='MECHANIC'?
      <Form.Group controlId="Hourly rate">
        <Form.Label>Hourly Rate</Form.Label>
        <Form.Control type="number" onChange={hourlyRateHandler} min={0}/>
      </Form.Group>:null}
    </Form>
  );
};

export default AddUserForm;
