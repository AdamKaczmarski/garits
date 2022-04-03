import Dropdown from "react-bootstrap/Dropdown";
import { INVENTORY } from "../../dummy-data/inventory";

const PaymentRetail = (props) => {
  /** CHANGE THE PROPS HERE FOR PART BECAUSE SOMETHING DIDN'T WORK FOR ME */
  const type =
    props.paymentRetail.cashOrCard.charAt(0).toUpperCase() +
    props.paymentRetail.cashOrCard.slice(1).toLowerCase();
    const formattedDateArrival = new Date(props.paymentRetail.paymentDate)
    .toISOString()
    .substring(0, 10);
  return (
    <tr>
      <td>{props.paymentRetail.idPayment}</td>
      <td>{formattedDateArrival}</td>
      <td>{props.paymentRetail.customer.name}</td>
      <td>{type}</td>
      <td>
        {(
          Math.round(
            props.paymentRetail.amount * 100
          ) / 100
        ).toFixed(2) + " GBP"}
      </td>
      <td>{type}</td>
      <td colSpan={2}>
        <Dropdown>
          <Dropdown.Toggle variant="secondary">Action</Dropdown.Toggle>

          <Dropdown.Menu>
            <Dropdown.Item>Download invoice</Dropdown.Item>
            <Dropdown.Item
              style={{ backgroundColor: "rgba(242, 97, 99,0.2)" }}
              onClick={()=>{props.deletePaymentRetail(props.paymentRetail.idPayment)}}
            >
              Delete
            </Dropdown.Item>
          </Dropdown.Menu>
        </Dropdown>
      </td>
      <td></td>
    </tr>
  );
};

export default PaymentRetail;
