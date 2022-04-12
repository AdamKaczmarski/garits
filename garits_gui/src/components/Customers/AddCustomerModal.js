import { useState, useContext } from "react";
import Modal from "react-bootstrap/Modal";
import AddCustomerForm from "./AddCustomerForm";
import Button from "react-bootstrap/Button";
import axios from "axios";
import CustomerClass from '../../models/Customer';
import AuthContext from "../../store/auth-context";
const AddCustomerModal = (props) => {
  const [customer, setCustomer] = useState(new CustomerClass())
  const authCtx = useContext(AuthContext);
  const addCustomer = async()=>{
    try {
      const response = await axios({
        method:"POST",
        url:"http://localhost:8080/customers",
        data: customer,
        headers:{'Authorization': `Bearer ${authCtx.authData.token}`}

      });
      console.log(response);
    } catch (err) {
      console.log(err)
    } finally {
      props.obtainCustomers();
    }
  }
  return (
    <Modal
      size="lg"
      show={props.show}
      onHide={props.onClose}
      backdrop="static"
    >
      <Modal.Header closeButton>
        <Modal.Title>Add customer</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <AddCustomerForm customer={customer} setCustomer={setCustomer}/>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="danger" onClick={props.onClose}>
          Close
        </Button>
        <Button variant="primary" onClick={addCustomer}>
          Add
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default AddCustomerModal;
