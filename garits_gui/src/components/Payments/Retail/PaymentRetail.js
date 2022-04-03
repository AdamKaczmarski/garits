import { useState } from "react";
import Dropdown from "react-bootstrap/Dropdown";
import Button from "react-bootstrap/Button";
import PaymentModal from "../PaymentModal";
import PaymentRetailDetails from "./PaymentRetailDetails";
const PaymentRetail = (props) => {
  const [showDetails, setShowDetails] = useState(false);
  const handleShowDetails = () => setShowDetails(!showDetails);
  const type =
    props.paymentRetail.cashOrCard.charAt(0).toUpperCase() +
    props.paymentRetail.cashOrCard.slice(1).toLowerCase();
  const formattedDateArrival = new Date(props.paymentRetail.paymentDate)
    .toISOString()
    .substring(0, 10);
  return (
    <>
      <tr>
        <td>{props.paymentRetail.idPayment}</td>
        <td>{formattedDateArrival}</td>
        <td>{props.paymentRetail.customer.name}</td>
        <td>{type}</td>
        <td>
          {(Math.round(props.paymentRetail.amount * 100) / 100).toFixed(2) +
            " GBP"}
        </td>
        <td>
          <Dropdown className="m-0 p-0" align={"end"}>
            <Dropdown.Toggle variant="secondary">Action</Dropdown.Toggle>
            <Dropdown.Menu>
              <Dropdown.Item>Download invoice</Dropdown.Item>
              {props.paymentRetail.createDate ===
              new Date().toISOString().substring(0, 10) ? (
                <Dropdown.Item
                  style={{ backgroundColor: "rgba(242, 97, 99,0.2)" }}
                  onClick={() => {
                    props.deletePaymentRetail(props.paymentRetail.idPayment);
                  }}
                >
                  Delete
                </Dropdown.Item>
              ) : null}
            </Dropdown.Menu>
          </Dropdown>{" "}
        </td>
        <td>
          <Button variant="info" onClick={handleShowDetails}>
            Details
          </Button>
        </td>
        <td></td>
        <PaymentModal
          title={`Payment details for ID: ${props.paymentRetail.idPayment}`}
          show={showDetails}
          submitAction={null}
          onClose={handleShowDetails}
          form={
            <PaymentRetailDetails idPayment={props.paymentRetail.idPayment} />
          }
        />
      </tr>
    </>
  );
};

export default PaymentRetail;
