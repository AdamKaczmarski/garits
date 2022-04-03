import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";
import { useState, useEffect } from "react";
import Customer from "./Customer";
import { CUSTOMERS } from "../../dummy-data/customers";
import AddCustomerModal from "./AddCustomerModal";
import axios from "axios";
import Spinner from "react-bootstrap/Spinner";
const CustomersTable = (props) => {
  const [showAdd, setShowAdd] = useState(false);
  const handleShowAdd = () => setShowAdd(!showAdd);
  const [customers, setCustomers] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  /*   const customers = CUSTOMERS.map((customer) => (
    <Customer key={customer.id} customer={customer} />
  )); */
  const deleteCustomer = async (id) => {
    try {
      const response = axios({
        method: "DELETE",
        url: "http://localhost:8080/customers/" + id,
      });
      console.log(response);
    } catch (err) {
      console.log(err);
    } finally {
      obtainCustomers();
    }
  };

  let customersView;

  const obtainCustomers = async () => {
    try {
      const response = await axios({
        method: "GET",
        url: "http://localhost:8080/customers",
      });
      setCustomers(response.data);
    } catch (err) {
      console.log(err);
    } finally {
      setIsLoading(false);
    }
  };
  useEffect(() => {
    obtainCustomers();
  }, []);
  if (isLoading) {
    return <Spinner animation="border" variant="primary" />;
  }
  if (customers && customers.length > 0) {
    customersView = customers.map((customer) => (
      <Customer
        key={customer.idCustomer}
        customer={customer}
        deleteCustomer={deleteCustomer}
      />
    ));
  }
  return (
    <>
      <Table striped hover className="mt-3">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th
              style={{ display: "flex" }}
              className="justify-content-evenly  "
            >
              Actions{" "}
              <Button variant="outline-primary" onClick={handleShowAdd}>
                +
              </Button>
            </th>
          </tr>
        </thead>
        <tbody>{customersView}</tbody>
      </Table>
      <AddCustomerModal show={showAdd} onClose={handleShowAdd} />
    </>
  );
};

export default CustomersTable;
