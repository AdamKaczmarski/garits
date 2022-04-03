import { useState } from "react";
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";
import InventoryModal from "../InventoryModal";
import { ORDERS } from "../../../dummy-data/orders";
import Order from "./Order";
import AddOrderForm from "./AddOrderForm";
const OrdersTable = () => {
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);

  const orders = ORDERS.map(order=><Order key={order.id_order} order={order}/>);

  return (
    <>
      <Table striped hover className="mt-3">
        <thead>
          <tr>
            <th>Order ID</th>
            <th>Order Date</th>
            <th>Order Status</th>
            <th>Total amount</th>
            <th>Description</th>
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
        <tbody>{orders}</tbody>
      </Table>
      <InventoryModal
        show={show}
        onClose={handleShow}
        title="Add order"
        form={<AddOrderForm />}
      />
      <InventoryModal />
    </>
  );
};

export default OrdersTable;
