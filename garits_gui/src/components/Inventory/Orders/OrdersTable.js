import { useState } from "react";
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";
import InventoryModal from "../InventoryModal";

const OrdersTable = () => {
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);
  return (
    <>
      <Table striped hover className="mt-3">
        <thead>
          <tr>
            <th>Sale ID</th>
            <th>Sale Date</th>
            <th>Part Name</th>
            <th>Quantity</th>
            <th>Part Price</th>
            <th>Total amount</th>
            <th>
              <span className="pr-3">Actions</span>
            </th>
            <th>
              <Button variant="outline-primary" onClick={handleShow}>
                +
              </Button>
            </th>
          </tr>
        </thead>
        <tbody>{}</tbody>
      </Table>
      <InventoryModal
        show={show}
        onClose={handleShow}
        title="Add order"
        form={null}
      />
    </>
  );
};

export default OrdersTable;
