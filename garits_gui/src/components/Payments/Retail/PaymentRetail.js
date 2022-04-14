import { useState, useContext } from "react";
import AuthContext from "../../../store/auth-context";
import Dropdown from "react-bootstrap/Dropdown";
import Button from "react-bootstrap/Button";
import PaymentModal from "../PaymentModal";
import PaymentRetailDetails from "./PaymentRetailDetails";
import axios from "axios";
const PaymentRetail = (props) => {
  const authCtx=useContext(AuthContext);
  const [showDetails, setShowDetails] = useState(false);
  const handleShowDetails = () => setShowDetails(!showDetails);
  let type = null;
  if (props.paymentRetail.cashOrCard) {
    type =
      props.paymentRetail.cashOrCard.charAt(0).toUpperCase() +
      props.paymentRetail.cashOrCard.slice(1).toLowerCase();
  }
  let formattedDateArrival;
  if (props.paymentRetail.paymentDate) {
    formattedDateArrival = new Date(props.paymentRetail.paymentDate)
      .toISOString()
      .substring(0, 10);
  }
/*   const downloadInvoice = async () => {
    try {
      const response = await axios({
        method: "GET",
        url: "http://localhost:8080/pdf/invoice/" + props.paymentRetail.idPayment,
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
 */
  return (
    <>
      <tr>
        <td>{props.paymentRetail.idPayment}</td>
        <td>{formattedDateArrival}</td>
        <td>{type}</td>
        <td>
          {(Math.round(props.paymentRetail.amount * 100) / 100).toFixed(2) +
            " GBP"}
        </td>
        <td>
          <Dropdown className="m-0 p-0" align={"end"}>
            <Dropdown.Toggle variant="secondary">Action</Dropdown.Toggle>
            <Dropdown.Menu>
              {/* <Dropdown.Item onClick={downloadInvoice}>Download invoice</Dropdown.Item> */}
              {props.paymentRetail.createDate ===
              new Date().toISOString().substring(0, 10) && authCtx.authData.role === "ROLE_FRANCHISEE" ? 
                (<Dropdown.Item
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
