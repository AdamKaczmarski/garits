import { useState } from "react";
import Dropdown from "react-bootstrap/Dropdown";
import Button from "react-bootstrap/Button";
import InventoryModal from "../InventoryModal";
import ChangeOrderStatusForm from './ChangeOrderStatusForm';
import OrderDetails from "./OrderDetails";
const Order = (props) => {
  const [showChange, setShowChange] = useState(false);
  const [showDetails, setShowDetails] = useState(false);
  const handleShowChange = () => setShowChange(!showChange);
  const handleShowDetails = () => setShowDetails(!showDetails);

  return (
    <>
      <tr>
        <td>{props.order.id_order}</td>
        <td>{props.order.status.charAt(0).toUpperCase() +
            props.order.status.slice(1).toLowerCase()}</td>
        <td>{props.order.order_date}</td>
        <td>{(Math.round(props.order.order_amount * 100) / 100).toFixed(2) + " GBP"}</td>
        <td>{props.order.description}</td>
        <td>
          <Dropdown className="m-0 p-0" align={"end"}>
            <Dropdown.Toggle variant="secondary">Action</Dropdown.Toggle>
            <Dropdown.Menu>
              <Dropdown.Item onClick={handleShowChange}>Change status</Dropdown.Item>
              <Dropdown.Item>Download report</Dropdown.Item>
              <Dropdown.Item
                style={{ backgroundColor: "rgba(242, 97, 99,0.2)" }}
                href="#/action-3"
              >
                Delete
              </Dropdown.Item>
            </Dropdown.Menu>
          </Dropdown>{" "}</td><td>
          <Button variant="info" onClick={handleShowDetails}>
            Details
          </Button>
        </td>
      </tr>
      <InventoryModal show={showChange} onClose={handleShowChange} title="Change status" form={<ChangeOrderStatusForm id_order={props.order.id_order} status={props.order.status}/>}/>
            <InventoryModal show={showDetails} onClose={handleShowDetails} title="Order details" form={<OrderDetails order={props.order}/>} />
    </>
  );
};

export default Order;
