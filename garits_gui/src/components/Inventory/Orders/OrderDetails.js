import { useState, useEffect, useCallback } from "react";
import axios from "axios";
import Table from "react-bootstrap/Table";
import Spinner from "react-bootstrap/Spinner";

const OrderDetails = (props) => {
  const [isLoading, setIsLoading] = useState(true);
  const [orderDetails, setOrderDetails] = useState([]);
  const obtainOrderDetails = useCallback(async () => {
    try {
      const response = await axios({
        method: "GET",
        url: `http://localhost:8080/partsorders/${props.idOrder}`,
      });
      console.log(response);
      setOrderDetails(response.data);
    } catch (err) {
      console.log(err);
    } finally {
      setIsLoading(false);
    }
  }, [props]);
  useEffect(() => {
    obtainOrderDetails();
  }, [obtainOrderDetails]);
  if (isLoading) {
    return <Spinner animation="border" variant="primary" />;
  }
  let partsOrder;
  if (orderDetails && orderDetails.length > 0) {
    partsOrder = orderDetails.map((oD, index) => (
      <tr key={index}>
        <td>{oD.part.code}</td>
        <td>{oD.part.partName}</td>
        <td>{oD.part.price}</td>
        <td>{oD.quantityOrdered}</td>
        <td>
          {(Math.round(oD.quantityOrdered * oD.part.price * 100) / 100).toFixed(
            2
          ) + " GBP"}
        </td>
      </tr>
    ));
  }

  return (
    <>
      <Table hover striped>
        <thead>
          <tr>
            <th>Code</th>
            <th>Part Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Total price</th>
          </tr>
        </thead>
        <tbody>{partsOrder}</tbody>
      </Table>
    </>
  );
};

export default OrderDetails;
