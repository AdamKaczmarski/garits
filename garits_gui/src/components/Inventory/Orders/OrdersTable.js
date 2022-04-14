import { useState, useEffect, useContext } from "react";
import axios from "axios";
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";
import InventoryModal from "../InventoryModal";
import Order from "./Order";
import AddOrderForm from "./AddOrderForm";
import Spinner from "react-bootstrap/Spinner";
import AuthContext from "../../../store/auth-context";
const OrdersTable = () => {
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);
  const [isLoading, setIsLoading] = useState(true);
  const [orders, setOrders] = useState([]);
  const authCtx = useContext(AuthContext);
  const obtainOrders = async () => {
    try {
      const response = await axios({
        method: "GET",
        url: "http://localhost:8080/orders",
        headers: { Authorization: `Bearer ${authCtx.authData.token}` },
      });
      console.log(response);
      setOrders(response.data);
    } catch (err) {
      console.log(err);
    } finally {
      setIsLoading(false);
    }
  };
  const deleteOrder = async (id) => {
    try {
      setIsLoading(true);
      const response = await axios({
        method: "DELETE",
        url: `http://localhost:8080/orders/${id}`,
        headers: { Authorization: `Bearer ${authCtx.authData.token}` },
      });
      console.log(response);
    } catch (err) {
      console.log(err);
    } finally {
      obtainOrders();
    }
  };
  const completeOrder = async (idOrder, newArrivalOrder) => {
    try {
      const response = await axios({
        method: "PATCH",
        url: `http://localhost:8080/orders/${idOrder}`,
        headers: { Authorization: `Bearer ${authCtx.authData.token}` },
        data: newArrivalOrder,
      });
      console.log(response);
    } catch (err) {
      console.log(err);
    } finally {
      obtainOrders();
    }
  };

  const changeOrderStatus = async (id, newStatus) => {
    try {
      setIsLoading(true);
      const response = await axios({
        method: "PATCH",
        url: `http://localhost:8080/orders/${id}/status`,
        headers: { Authorization: `Bearer ${authCtx.authData.token}` },
        data: newStatus,
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
    <Order
      key={order.idOrder}
      order={order}
      deleteOrder={deleteOrder}
      changeOrderStatus={changeOrderStatus}
      completeOrder={completeOrder}
    />
  ));
  const newOrder = {
    status: "ordered",
    description: "",
    orderDate: new Date().toISOString().substring(0, 10),
    orderArrival: null,
    company:"",
    address:"",
    phoneNo:"",
    fax:""
  };
  const orderItems = [];
  const addOrder = async () => {
    try {
      setIsLoading(true);
      const response = await axios({
        method: "POST",
        url: "http://localhost:8080/orders",
        data: newOrder,
        headers: { Authorization: `Bearer ${authCtx.authData.token}` },
      });
      console.log(response);
      const idOrder = response.data.idOrder;
      addItemsToOrder(idOrder);
    } catch (err) {
      console.log(err);
    } finally {
      handleShow();
    }
  };
  const addItemsToOrder = async (idOrder) => {
    try {
      setIsLoading(true);
      const response = await axios({
        method: "POST",
        url: `http://localhost:8080/orders/${idOrder}/items`,
        data: orderItems,
        headers: { Authorization: `Bearer ${authCtx.authData.token}` },
      });
      console.log(response);
    } catch (err) {
      console.log(err);
    } finally {
      handleShow();
      obtainOrders();
    }
  };

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
        form={<AddOrderForm newOrder={newOrder} orderItems={orderItems} />}
      />
      <InventoryModal />
    </>
  );
};

export default OrdersTable;
