import Dropdown from "react-bootstrap/Dropdown";
import Button from "react-bootstrap/Button";
import { useState } from "react";
import CustomerModal from "./CustomerModal";
import CustomerDetailsTabs from "./CustomerDetailsTabs";
import EditCustomer from "./EditCustomer";

const Customer = (props) => {
  const [showDetails, setShowDetails] = useState(false);
  const handleShowDetails = () => setShowDetails(!showDetails);
  const [showEdit, setShowEdit] = useState(false);
  const handleShowEdit = () => setShowEdit(!showEdit);

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
            <Dropdown.Item onClick={handleShowEdit}>Edit</Dropdown.Item>
            <Dropdown.Item
              style={{ backgroundColor: "rgba(242, 97, 99,0.2)" }}
              href="#/action-3"
            >
              Delete
            </Dropdown.Item>
          </Dropdown.Menu>
        </Dropdown>{" "}
        <Button variant="info" onClick={handleShowDetails}>
          Details
        </Button>
      </td>
      <CustomerModal
        title={props.customer.name}
        show={showDetails}
        onClose={handleShowDetails}
        form={<CustomerDetailsTabs customer={props.customer} /* setIsReportsTab={setIsReportsTab} *//>
      }
      />
      <CustomerModal title={"Edit "+props.customer.name} show={showEdit} onClose={handleShowEdit} form={<EditCustomer customer={props.customer} />}/>
    </tr>
  );
};

export default Customer;
