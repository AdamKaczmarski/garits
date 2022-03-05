import { useState } from "react";
import Dropdown from "react-bootstrap/Dropdown";
import PaymentModal from "./PaymentModal";
import SetPayDate from "./SetPayDate";

const PaymentJob = (props) => {
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);

  const type =
    props.payment.payment_type.charAt(0).toUpperCase() +
    props.payment.payment_type.slice(1).toLowerCase();
  return (
    <>
      <tr>
        <td>{props.payment.id}</td>
        <td>{props.payment.customer_name}</td>
        <td>{type}</td>
        <td>
          {(Math.round(props.payment.amount * 100) / 100).toFixed(2) + " GBP"}
        </td>
        <td>{props.payment.job_id}</td>
        <td>{props.payment.create_date}</td>
        <td>{props.payment.payment_date}</td>
        <td>{props.payment.payment_due}</td>
        <td colSpan={2}>
          <Dropdown>
            <Dropdown.Toggle variant="secondary">Action</Dropdown.Toggle>

            <Dropdown.Menu>
              <Dropdown.Item>Download invoice</Dropdown.Item>
              {props.payment.payment_date === null ? (
                <Dropdown.Item onClick={handleShow}>Set payment date</Dropdown.Item>
              ) : null}
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
      {props.payment.payment_date === null ? (
        <PaymentModal
          show={show}
          onClose={handleShow}
          title="Set payment date"
          form={<SetPayDate id={props.payment.id} />}
        />
      ) : null}
    </>
  );
};

export default PaymentJob;
