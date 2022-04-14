import Dropdown from "react-bootstrap/Dropdown";
import Button from "react-bootstrap/Button";
import { useState, useContext } from "react";
import CustomerModal from "./CustomerModal";
import CustomerDetailsTabs from "./CustomerDetailsTabs";
import EditCustomer from "./EditCustomer";
import CustomerClass from "../../models/Customer";
import axios from "axios";
import AddFlexDiscount from "./AddFlexDiscount";
import AddVarDiscount from "./AddVarDiscount";
import CustomModal from "../CommonComponents/CustomModal";
import AuthContext from "../../store/auth-context";
import ChangeAccountStatus from "./ChangeAccStatus";
const Customer = (props) => {
  const [showDetails, setShowDetails] = useState(false);
  const handleShowDetails = () => setShowDetails(!showDetails);
  const [showEdit, setShowEdit] = useState(false);
  const handleShowEdit = () => setShowEdit(!showEdit);
  const [showVar, setShowVar] = useState(false);
  const handleShowVar = () => setShowVar(!showVar);
  const [showFlex, setShowFlex] = useState(false);
  const handleShowFlex = () => setShowFlex(!showFlex);
  const [showAccStatus, setShowAccStatus] = useState(false);
  const handleShowAccStatus = () => setShowAccStatus(!showAccStatus);
  const authCtx = useContext(AuthContext);
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
        headers: { Authorization: `Bearer ${authCtx.authData.token}` },
      });
      console.log(response);
    } catch (err) {
      console.log(err);
    } finally {
      props.obtainCustomers();
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
        headers: { Authorization: `Bearer ${authCtx.authData.token}` },
      });
      console.log(response);
    } catch (err) {
      console.log(err);
    } finally {
      props.obtainCustomers();
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
        headers: { Authorization: `Bearer ${authCtx.authData.token}` },
      });
      if (response.status !== 200) {
        handleShowVar();
      } else {
        console.log(response);
      }
    } catch (err) {
      console.log(err);
    } finally {
      //window.location.reload();
      props.obtainCustomers();
    }
  };
  let decision = {accountHolder:props.customer.accountHolder};
  const changeAccountStatus = async() => {
    try {
      const response=await axios({
        method: "PATCH",
        url:`http://localhost:8080/customers/account-holding/${props.customer.idCustomer}/${!props.customer.accountHolder}`,
        headers: { Authorization: `Bearer ${authCtx.authData.token}` },
      })
    } catch(err) {
      console.log(err)
    } finally {
      props.obtainCustomers();
    }
  }
  return (
    <tr>
      <td>{props.customer.idCustomer}</td>
      <td>{props.customer.name}</td>
      <td>{props.customer.email}</td>
      <td style={{ display: "flex", justifyContent: "space-evenly" }}>
        <Dropdown className="m-0 p-0" align={"end"}>
          <Dropdown.Toggle variant="secondary">Action</Dropdown.Toggle>

          <Dropdown.Menu>
            {/* <Dropdown.Item>Generate monthly report</Dropdown.Item> */}
            {authCtx.authData.role === "ROLE_FRANCHISEE" ? (
              <>
                <Dropdown.Item onClick={handleShowEdit}>Edit</Dropdown.Item>
                {props.customer.accountHolder ? (
                  <>
                    <Dropdown.Item onClick={handleShowVar}>
                      Add Variable Discount
                    </Dropdown.Item>
                    <Dropdown.Item onClick={handleShowFlex}>
                      Add Flexible Discount
                    </Dropdown.Item>
                  </>
                ) : null}
                <Dropdown.Item onClick={handleShowAccStatus}>Change account status</Dropdown.Item>
                <Dropdown.Item
                  style={{ backgroundColor: "rgba(242, 97, 99,0.2)" }}
                  href="#/action-3"
                >
                  Delete
                </Dropdown.Item>
              </>
            ) : null}
          </Dropdown.Menu>
        </Dropdown>{" "}
        <Button variant="info" onClick={handleShowDetails}>
          Details
        </Button>
      </td>
      <CustomModal
        title={props.customer.name}
        show={showDetails}
        onClose={handleShowDetails}
        submitAction={null}
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
        title={"Change account status"}
        show={showAccStatus}
        onClose={handleShowAccStatus}
        submitAction={changeAccountStatus}
        form={<ChangeAccountStatus accountHolder={props.customer.accountHolder} />}
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
