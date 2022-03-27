import Dropdown from "react-bootstrap/Dropdown";
import Button from "react-bootstrap/Button";
import { useState } from "react";
import CustomerModal from "./CustomerModal";
import CustomerDetailsTabs from "./CustomerDetailsTabs";
import EditCustomer from "./EditCustomer";
import CustomerClass from "../../models/Customer";
import axios from "axios";
import AddFlexDiscount from "./AddFlexDiscount";
import AddVarDiscount from "./AddVarDiscount";
const Customer = (props) => {
  const [showDetails, setShowDetails] = useState(false);
  const handleShowDetails = () => setShowDetails(!showDetails);
  const [showEdit, setShowEdit] = useState(false);
  const handleShowEdit = () => setShowEdit(!showEdit);
  const [showVar, setShowVar] = useState(false);
  const handleShowVar = () => setShowVar(!showVar);
  const [showFlex, setShowFlex] = useState(false);
  const handleShowFlex = () => setShowFlex(!showFlex);
  const [customer, setCustomer] = useState({
    ...new CustomerClass(),
    ...props.customer,
  });
  const varDiscount = {
    serviceId: 0,
    discount: 0,
  };
  const flexDiscount = {
    rangeFrom: 0,
    discount: 0,
  };
  // console.log({...new CustomerClass(),...props.customer})
  const editCustomer = () => {
    console.log(customer);
    try {
      const response = axios({
        method: "PATCH",
        url: "http://localhost:8080/customers/" + props.customer.idCustomer,
        data: customer,
      });
      console.log(response);
    } catch (err) {
      console.log(err);
    } finally {
      window.location.reload();
    }
  };
  const addFlexDiscount = () => {
    try {
      const response = axios({
        method: "POST",
        url:
          "http://localhost:8080/customers/" +
          props.customer.idCustomer +
          "/flexDiscount",
        data: flexDiscount,
      });
      console.log(response);
    } catch (err) {
      console.log(err);
    } finally {
      window.location.reload();
    }
  };
  const addVarDiscount = () => {
    try {
      const response = axios({
        method: "POST",
        url:
          "http://localhost:8080/customers/" +
          props.customer.idCustomer +
          "/varDiscount",
        data: varDiscount,
      });
      console.log(response);
    } catch (err) {
      console.log(err);
    } finally {
      window.location.reload();
    }
  };
  return (
    <tr>
      <td>{props.customer.idCustomer}</td>
      <td>{props.customer.name}</td>
      <td>{props.customer.email}</td>
      <td style={{ display: "flex", justifyContent: "space-evenly" }}>
        <Dropdown className="m-0 p-0" align={"end"}>
          <Dropdown.Toggle variant="secondary">Action</Dropdown.Toggle>

          <Dropdown.Menu>
            <Dropdown.Item>Generate monthly report</Dropdown.Item>
            <Dropdown.Item onClick={handleShowEdit}>Edit</Dropdown.Item>
            <Dropdown.Item onClick={handleShowVar}>
              Add Variable Discount
            </Dropdown.Item>
            <Dropdown.Item onClick={handleShowFlex}>
              Add Flexible Discount
            </Dropdown.Item>
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
        form={
          <CustomerDetailsTabs
            customer={props.customer}
            /* setIsReportsTab={setIsReportsTab} */ submitAction={
              handleShowDetails
            }
          />
        }
      />
      <CustomerModal
        title={"Edit " + props.customer.name}
        show={showEdit}
        onClose={handleShowEdit}
        submitAction={editCustomer}
        form={<EditCustomer customer={customer} setCustomer={setCustomer} />}
      />
      <CustomerModal
        title={"Add Flexible Discount"}
        show={showFlex}
        onClose={handleShowFlex}
        submitAction={addFlexDiscount}
        form={<AddFlexDiscount flexDiscount={flexDiscount} />}
      />
      <CustomerModal
        title={"Add Variable Discount"}
        show={showVar}
        onClose={handleShowVar}
        submitAction={addVarDiscount}
        form={
          <AddVarDiscount
            varDiscount={varDiscount}
            customerVarDiscounts={props.customer.variableDiscounts}
            //setFlexDiscount={setVarDiscount}
          />
        }
      />
    </tr>
  );
};

export default Customer;
