import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";
import { useState } from "react";
import Customer from './Customer';
import { CUSTOMERS } from "../../dummy-data/customers";
import AddCustomerModal from './AddCustomerModal';
const CustomersTable = (props) => {
  const [showAdd, setShowAdd] = useState(false);
  const handleShowAdd = () => setShowAdd(!showAdd);
  
  const customers = CUSTOMERS.map((customer) => (
    <Customer key={customer.id} customer={customer} />
  ));
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
        <tbody>{customers}</tbody>
      </Table>
      <AddCustomerModal show={showAdd} onClose={handleShowAdd}/>
    </>
  );
};

export default CustomersTable;
