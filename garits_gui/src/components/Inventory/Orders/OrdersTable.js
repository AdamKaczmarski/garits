import { useState, useEffect } from "react";
import axios from "axios";
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";
import InventoryModal from "../InventoryModal";
import Order from "./Order";
import AddOrderForm from "./AddOrderForm";
import Spinner from "react-bootstrap/Spinner";
const OrdersTable = () => {
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);
  const [isLoading, setIsLoading] = useState(true);
  const [orders, setOrders] = useState([]);
  const newOrder = {};
  const obtainOrders = async () => {
    try {
      const response = await axios({
        method: "GET",
        url: "http://localhost:8080/orders",
      });
      console.log(response);
      setOrders(response.data);
    } catch (err) {
      console.log(err);
    } finally {
      setIsLoading(false);
    }
  };
  const addOrder = async () => {
    try {
      const response = await axios({
        method: "POST",
        url: "http://localhost:8080/orders",
        data: newOrder,
      });
      console.log(response);
    } catch (err) {
      console.log(err);
    } finally {
      obtainOrders();
    }
  };

  useEffect(() => {
    obtainOrders();
  }, []);
  if (isLoading) {
    return <Spinner animation="border" variant="primary" />;
  }
  const ordersView = orders.map((order) => (
    <Order key={order.idOrder} order={order} />
  ));
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
            <th>Order Arrival</th>
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
        <tbody>{ordersView}</tbody>
      </Table>
      <InventoryModal
        show={show}
        onClose={handleShow}
        title="Add order"
        submitAction={addOrder}
        form={<AddOrderForm newOrder={newOrder} />}
      />
      <InventoryModal />
    </>
  );
};

export default OrdersTable;
