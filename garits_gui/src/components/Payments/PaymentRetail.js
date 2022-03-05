import Dropdown from "react-bootstrap/Dropdown";
import { INVENTORY } from "../../dummy-data/inventory";

const PaymentRetail = (props) => {
  /** CHANGE THE PROPS HERE FOR PART BECAUSE SOMETHING DIDN'T WORK FOR ME */
  const type =
    props.paymentRetail.payment_type.charAt(0).toUpperCase() +
    props.paymentRetail.payment_type.slice(1).toLowerCase();

  return (
    <tr>
      <td>{props.paymentRetail.id_sale}</td>
      <td>{props.paymentRetail.sale_date}</td>
      <td>{props.paymentRetail.part_name}</td>
      <td>
        {(Math.round(props.paymentRetail.price * 100) / 100).toFixed(2) +
          " GBP"}
      </td>
      <td>{props.paymentRetail.quantity_sold}</td>
      <td>
        {(
          Math.round(
            props.paymentRetail.quantity_sold * props.paymentRetail.price * 100
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
              href="#/action-3"
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
