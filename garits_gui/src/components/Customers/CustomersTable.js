import { useState, useEffect, useContext } from "react";
import axios from "axios";
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";
import Customer from "./Customer";
//import { CUSTOMERS } from "../../dummy-data/customers";
import AddCustomerModal from "./AddCustomerModal";
import Spinner from "react-bootstrap/Spinner";
import AuthContext from "../../store/auth-context";
const CustomersTable = (props) => {
  const [showAdd, setShowAdd] = useState(false);
  const handleShowAdd = () => setShowAdd(!showAdd);
  const [customers, setCustomers] = useState([]);
  const [isLoading, setIsLoading]=useState(true);
  const authCtx = useContext(AuthContext);
  /*   const customers = CUSTOMERS.map((customer) => (
    <Customer key={customer.id} customer={customer} />
  )); */
  let customersView;
  useEffect(() => {
    obtainCustomers();
  }, []);

  const obtainCustomers = async () => {
    try {
      const response = await axios({
        method: "GET",
        url: "http://localhost:8080/customers",
        headers:{'Authorization': `Bearer ${authCtx.authData.token}`}

      });
      setCustomers(response.data);
    } catch (err) {
      console.log(err);
    } finally {
      setIsLoading(false)
    }
  };
  if (customers && customers.length > 0) {
    customersView = customers.map((customer) => (
      <Customer key={customer.idCustomer} customer={customer} obtainCustomers={obtainCustomers}/>
    ));
  }
if (isLoading){
  return  <Spinner animation="border" variant="primary" />
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
        <AddCustomerModal show={showAdd} obtainCustomers={obtainCustomers} onClose={handleShowAdd} />
      
    </>
  );
};

export default CustomersTable;
