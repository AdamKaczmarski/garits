import Table from "react-bootstrap/Table";

const OrderDetails = (props) => {
  const partsOrder = props.order.parts.map((part, index) => (
    <tr key={index}>
      <td>{part.code}</td>
      <td>{part.part_name}</td>
      <td>{part.price}</td>
      <td>{part.quantity_ordered}</td>
      <td>
        {(Math.round(part.quantity_ordered * part.price * 100) / 100).toFixed(
          2
        ) + " GBP"}
      </td>
    </tr>
  ));
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
