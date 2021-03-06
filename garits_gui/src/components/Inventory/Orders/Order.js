import { useState,useContext } from "react";
import AuthContext from "../../../store/auth-context";
import axios from "axios";
import Dropdown from "react-bootstrap/Dropdown";
import Button from "react-bootstrap/Button";
import InventoryModal from "../InventoryModal";
import ChangeOrderStatusForm from "./ChangeOrderStatusForm";
import OrderDetails from "./OrderDetails";
import CompleteOrderForm from "./CompleteOrderForm";
const Order = (props) => {
  const authCtx=useContext(AuthContext);
  const [showChange, setShowChange] = useState(false);
  const [showDetails, setShowDetails] = useState(false);
  const [showFinish, setShowFinish] = useState(false);
  const handleShowChange = () => setShowChange(!showChange);
  const handleShowDetails = () => setShowDetails(!showDetails);
  const handleShowFinish = () => setShowFinish(!showFinish);
  const newStatus = { status: "" };
  const downloadReport = async()=>{
    try {
      const response = await axios({
        method: "GET",
        url: "http://localhost:8080/pdf/parts-order/" + props.order.idOrder,
        headers: { Authorization: `Bearer ${authCtx.authData.token}` },
        responseType: 'blob'
      });
      if (response.status === 200) {
        const url = window.URL.createObjectURL(new Blob([response.data]));
        const link = document.createElement("a");
        link.href = url;
        link.setAttribute("download", "Order_"+props.order.idOrder+".pdf");
        document.body.appendChild(link);
        link.click();
      }
    } catch (err) {
      console.log(err);
    }
  }
  let formattedDateArrival = "";
  if (props.order.orderArrival != null) {
    formattedDateArrival = new Date(props.order.orderArrival)
      .toISOString()
      .substring(0, 10);
  }
  let formattedOrderDate = new Date(props.order.orderDate)
    .toISOString()
    .substring(0, 10);
  //Delete order
  const handleDelete = () => {
    props.deleteOrder(props.order.idOrder);
  };
  //Complete order
  const newArrivalOrder = {
    idOrder: props.order.idOrder,
    orderArrival: new Date().toISOString().substring(0, 10),
    status: "completed",
  };
  const handleComplete = () => {
    handleShowFinish();
    props.completeOrder(props.order.idOrder, newArrivalOrder);
  };
  return (
    <>
      <tr>
        <td>{props.order.idOrder}</td>
        <td>{formattedOrderDate}</td>

        <td>
          {props.order.status.charAt(0).toUpperCase() +
            props.order.status.slice(1).toLowerCase()}
        </td>
        <td>
          {(Math.round(props.order.orderAmount * 100) / 100).toFixed(2) +
            " GBP"}
        </td>
        <td>{props.order.description}</td>
        <td>{formattedDateArrival}</td>
        <td>
          <Dropdown className="m-0 p-0" align={"end"}>
            <Dropdown.Toggle variant="secondary">Action</Dropdown.Toggle>
            <Dropdown.Menu>
            <Dropdown.Item onClick={downloadReport}>Download report</Dropdown.Item>
            {props.order.status!=='completed'?(<><Dropdown.Item onClick={handleShowChange}>
                Change status
              </Dropdown.Item>
              
              {props.order.status==='ordered'?<Dropdown.Item onClick={handleShowFinish}>
                Complete order
              </Dropdown.Item>:null}</>):null}
              <Dropdown.Item
                style={{ backgroundColor: "rgba(242, 97, 99,0.2)" }}
                onClick={handleDelete}
              >
                Delete
              </Dropdown.Item>
            </Dropdown.Menu>
          </Dropdown>{" "}
        </td>
        <td>
          <Button variant="info" onClick={handleShowDetails}>
            Details
          </Button>
        </td>
      </tr>

      <InventoryModal
        show={showChange}
        onClose={handleShowChange}
        title="Change status"
        submitAction={() =>
          props.changeOrderStatus(props.order.idOrder, newStatus)
        }
        form={
          <ChangeOrderStatusForm
            status={props.order.status}
            newStatus={newStatus}
          />
        }
      />
      <InventoryModal
        show={showDetails}
        onClose={handleShowDetails}
        title="Order details"
        submitAction={null}
        form={<OrderDetails idOrder={props.order.idOrder} />}
      />
      <InventoryModal
        show={showFinish}
        onClose={handleShowFinish}
        submitAction={handleComplete}
        newArrivalOrder={newArrivalOrder}
        title={`Complete order ${props.order.idOrder}`}
        form={<CompleteOrderForm newArrivalOrder={newArrivalOrder} />}
      />
    </>
  );
};

export default Order;
