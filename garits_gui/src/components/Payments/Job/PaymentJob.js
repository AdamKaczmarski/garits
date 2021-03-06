import { useState, useContext } from "react";
import Dropdown from "react-bootstrap/Dropdown";
import PaymentModal from "../PaymentModal";
import SetPayDate from "./SetPayDate";
import axios from "axios";
import AuthContext from "../../../store/auth-context";
const PaymentJob = (props) => {
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);
  const authCtx = useContext(AuthContext);
  let isLate = false;
  let paymentDate, paymentDue;
  let finishPayment = {
    idPayment: props.paymentJob.idPayment,
    paymentDate: "",
    cashOrCard: "",
  };
  const createDate = new Date(props.paymentJob.createDate)
    .toISOString()
    .substring(0, 10);
  if (props.paymentJob.paymentDate)
    paymentDate = new Date(props.paymentJob.paymentDate)
      .toISOString()
      .substring(0, 10);
  if (props.paymentJob.paymentDue) {
    paymentDue = new Date(props.paymentJob.paymentDue)
      .toISOString()
      .substring(0, 10);
    isLate =
      new Date() > new Date(props.paymentJob.paymentDue) &&
      !props.paymentJob.paymentDate;
  }
  let type = null;
  if (props.paymentJob.cashOrCard) {
    type =
      props.paymentJob.cashOrCard.charAt(0).toUpperCase() +
      props.paymentJob.cashOrCard.slice(1).toLowerCase();
  }
/*   const deletePayment = async () => {
    try {
      await axios({
        method: "DELETE",
        url: `http://localhost:8080/payments-jobs/${props.paymentJob.idPayment}`,
        headers: { Authorization: `Bearer ${authCtx.authData.token}` },
      });
    } catch (err) {
      console.log(err);
    } finally {
      props.obtainPaymentsJobs();
    }
  };
 */  const completePayment = async () => {
    try {
      await axios({
        method: "PATCH",
        url: `http://localhost:8080/payments-jobs/${props.paymentJob.idPayment}/complete`,
        data: finishPayment,
        headers: { Authorization: `Bearer ${authCtx.authData.token}` },
      });
    } catch (err) {
      console.log(err);
    } finally {
      props.obtainPaymentsJobs();
    }
  };
  const downloadInvoice = async () => {
    try {
      const response = await axios({
        method: "GET",
        url: "http://localhost:8080/pdf/invoice/" + props.paymentJob.idPayment,
        headers: {Authorization: `Bearer ${authCtx.authData.token}`},
        responseType: "blob",
      });
      if (response.status === 200) {
        const url = window.URL.createObjectURL(new Blob([response.data]));
        const link = document.createElement("a");
        link.href = url;
        link.setAttribute("download", "Invoice.pdf");
        document.body.appendChild(link);
        link.click();
      }
    } catch (err) {
      console.log(err);
    }
  };
  return (
    <>
      <tr style={isLate ? { backgroundColor: "rgba(242, 97, 99,0.2)" } : null}>
        <td>{props.paymentJob.idPayment}</td>
        <td>{props.paymentJob.customer.name}</td>
        <td>{type}</td>
        <td>
          {(Math.round(props.paymentJob.amount * 100) / 100).toFixed(2) +
            " GBP"}
        </td>
        <td>{props.paymentJob.jobId}</td>
        <td>{createDate}</td>
        <td>{paymentDate}</td>
        <td>{isLate ? paymentDue : paymentDue}</td>
        <td colSpan={2}>
          <Dropdown>
            <Dropdown.Toggle variant="secondary">Action</Dropdown.Toggle>

            <Dropdown.Menu>
              <Dropdown.Item onClick={downloadInvoice}>Download invoice</Dropdown.Item>
              {props.paymentJob.paymentDate ? null : (
                <Dropdown.Item onClick={handleShow}>
                  Set payment as done
                </Dropdown.Item>
              )}
              {/* {authCtx.authData.role === "ROLE_FRANCHISEE" ? (
                <Dropdown.Item
                  style={{ backgroundColor: "rgba(242, 97, 99,0.2)" }}
                  onClick={deletePayment}
                >
                  Delete
                </Dropdown.Item>
              ) : null} */}
            </Dropdown.Menu>
          </Dropdown>
        </td>
        <td></td>
      </tr>
      {props.paymentJob.paymentDate ? null : (
        <PaymentModal
          show={show}
          onClose={handleShow}
          title="Set payment date"
          form={
            <SetPayDate
              id={props.paymentJob.idPayment}
              finishPayment={finishPayment}
            />
          }
          submitAction={completePayment}
        />
      )}
    </>
  );
};

export default PaymentJob;
