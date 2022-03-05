import Dropdown from "react-bootstrap/Dropdown";
import Button from "react-bootstrap/Button";
import { useState } from "react";
import CustomerDetailsModal from "./CustomerDetailsModal";
const Customer = (props) => {
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);

  return (
    <tr>
      <td>{props.customer.id}</td>
      <td>{props.customer.name}</td>
      <td>{props.customer.email}</td>
      <td style={{ display: "flex",justifyContent:"space-evenly" }}>
        <Dropdown className="m-0 p-0" align={"end"}>
          <Dropdown.Toggle variant="secondary">Action</Dropdown.Toggle>

          <Dropdown.Menu>
            <Dropdown.Item>Generate monthly report</Dropdown.Item>
            <Dropdown.Item>Edit</Dropdown.Item>
            <Dropdown.Item
              style={{ backgroundColor: "rgba(242, 97, 99,0.2)" }}
              href="#/action-3"
            >
              Delete
            </Dropdown.Item>
          </Dropdown.Menu>
        </Dropdown>{" "}
        <Button variant="info" onClick={handleShow}>
          Details
        </Button>
      </td>
      <CustomerDetailsModal
        customer={props.customer}
        show={show}
        onClose={handleShow}
      />
    </tr>
  );
};

export default Customer;
