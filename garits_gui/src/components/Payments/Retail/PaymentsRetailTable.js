import { useState, useEffect,useContext } from "react";
import axios from "axios";
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";
import AuthContext from "../../../store/auth-context";
import PaymentRetail from "./PaymentRetail";
import AddRetailPaymentForm from "./AddRetailPaymentForm";
import PaymentModal from "../PaymentModal";
import  Spinner  from "react-bootstrap/Spinner";

const PaymentsRetailTable = () => {
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);
  const [isLoading, setIsLoading] = useState(true);
  const [paymentsRetail, setPaymentsRetail] = useState([]);
  const authCtx = useContext(AuthContext);
  let paymentsRetailView;
  let newPayment = {
    cashOrCard: "",
    amount: 0,
    createDate: new Date().toISOString().substring(0, 10),
    paymentDate: new Date().toISOString().substring(0, 10),
    customer: { idCustomer: 0 },
  };
  let newPaymentItems = [];
  const obtainPaymentsRetail = async () => {
    try {
      const response = await axios({
        method: "GET",
        url: "http://localhost:8080/payments-retails",
        headers:{'Authorization': `Bearer ${authCtx.authData.token}`}

      });
      console.log(response);
      if (response.status === 200) setPaymentsRetail(response.data);
    } catch (e) {
      console.log(e);
    } finally {
      setIsLoading(false);
    }
  };
  const deletePaymentRetail = async (idPayment) => {
    try {
      const response = await axios({
        method: "DELETE",
        url: `http://localhost:8080/payments-retails/${idPayment}`,
        headers:{'Authorization': `Bearer ${authCtx.authData.token}`}

      });
      console.log(response);
    } catch (e) {
      console.log(e);
    } finally {
      obtainPaymentsRetail();
    }
  };
  const addItemsToPayment=async(idPayment)=>{
    try {
      setIsLoading(true);
      const response = await axios({
        method: "POST",
        url: `http://localhost:8080/payments-retail/${idPayment}/items`,
        data: newPaymentItems,
        headers:{'Authorization': `Bearer ${authCtx.authData.token}`}

      });
      console.log(response);
    } catch (err) {
      console.log(err);
    } finally {
      handleShow();
      obtainPaymentsRetail();
    }

  };
  const addPaymentRetail=async()=>{
    try{
      const response = await axios({
        method: "POST",
        url: "http://localhost:8080/payments-retails",
        data: newPayment,
        headers:{'Authorization': `Bearer ${authCtx.authData.token}`}

      });
      console.log(response);
      const idPayment = response.data.idPayment;
      addItemsToPayment(idPayment);
    }
    catch (err){
      console.log(err)
    } finally {
      handleShow();
    }
  }
  useEffect(() => {
    obtainPaymentsRetail();
  }, []);

  if (isLoading) {
    return <Spinner variant="primary" />;
  }
  if (paymentsRetail && paymentsRetail.length > 0) {
    paymentsRetailView = paymentsRetail.map((paymentRetail) => (
      <PaymentRetail
        key={paymentRetail.idPayment}
        paymentRetail={paymentRetail}
        deletePaymentRetail={deletePaymentRetail}
      />
    ));
  }

  return (
    <>
      <Table striped hover className="mt-3">
        <thead>
          <tr>
            <th>Sale ID</th>
            <th>Sale Date</th>
            <th>Payment Type</th>
            <th>Total amount</th>
            <th>
              <span>Actions</span>
            </th>
            <th>
              <Button variant="outline-primary" onClick={handleShow}>
                +
              </Button>
            </th>
          </tr>
        </thead>
        <tbody>{paymentsRetailView}</tbody>
      </Table>
      <PaymentModal
        show={show}
        onClose={handleShow}
        title="Add payment"
        form={
          <AddRetailPaymentForm
            paymentRetailItems={newPaymentItems}
            newPayment={newPayment}
          />
        }
        submitAction={addPaymentRetail}
      />
    </>
  );
};

export default PaymentsRetailTable;
