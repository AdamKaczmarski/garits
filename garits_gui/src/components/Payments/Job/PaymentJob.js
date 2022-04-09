import { useState } from "react";
import Dropdown from "react-bootstrap/Dropdown";
import PaymentModal from "../PaymentModal";
import SetPayDate from "../SetPayDate";

const PaymentJob = (props) => {
  console.log(props);
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);
  let isLate =false;
  let paymentDate, paymentDue;
  const createDate = new Date(props.paymentRetail.createDate)
    .toISOString()
    .substring(0, 10);
  if (props.paymentRetail.paymentDate)
    paymentDate = new Date(props.paymentRetail.paymentDate)
      .toISOString()
      .substring(0, 10);
  if (props.paymentRetail.paymentDue){
    paymentDue = new Date(props.paymentRetail.paymentDue)
      .toISOString()
      .substring(0, 10);
      isLate = new Date() > new Date(props.paymentRetail.paymentDue);
  } 
  let type = null;
  if (props.paymentRetail.cashOrCard) {
    type =
      props.paymentRetail.cashOrCard.charAt(0).toUpperCase() +
      props.paymentRetail.cashOrCard.slice(1).toLowerCase();
  }
  return (
    <>
      <tr style={isLate ? { backgroundColor: "rgba(242, 97, 99,0.2)" } : null}>
        <td>{props.paymentRetail.idPayment}</td>
        <td>{props.paymentRetail.customer.name}</td>
        <td>{type}</td>
        <td>
          {(Math.round(props.paymentRetail.amount * 100) / 100).toFixed(2) +
            " GBP"}
        </td>
        <td>{"JOB ID"}</td>
        <td>{createDate}</td>
        <td>{paymentDate}</td>
        <td>{isLate ? paymentDue : paymentDue}</td>
        <td colSpan={2}>
          <Dropdown>
            <Dropdown.Toggle variant="secondary">Action</Dropdown.Toggle>

            <Dropdown.Menu>
              <Dropdown.Item>Download invoice</Dropdown.Item>
              {props.paymentRetail.payment_date === null ? (
                <Dropdown.Item onClick={handleShow}>
                  Set payment date
                </Dropdown.Item>
              ) : null}
              <Dropdown.Item>
                Download invoice
              </Dropdown.Item>
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
      {props.paymentRetail.paymentDate === null ? (
        <PaymentModal
          show={show}
          onClose={handleShow}
          title="Set payment date"
          form={<SetPayDate id={props.paymentRetail.idPayment} />}
        />
      ) : null}
    </>
  );
};

export default PaymentJob;
