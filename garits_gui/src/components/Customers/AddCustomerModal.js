import { useState } from "react";
import Modal from "react-bootstrap/Modal";
import AddCustomerForm from "./AddCustomerForm";
import Button from "react-bootstrap/Button";
import axios from "axios";
import CustomerClass from '../../models/Customer';
const AddCustomerModal = (props) => {
  const [customer, setCustomer] = useState(new CustomerClass())
  const addCustomer = ()=>{
    try {
      const response = axios({
        method:"POST",
        url:"http://localhost:8080/customers",
        data: customer
      });
      console.log(response);
    } catch (err) {
      console.log(err)
    } finally {
      window.location.reload();
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
