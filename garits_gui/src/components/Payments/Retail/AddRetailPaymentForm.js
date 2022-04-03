import Form from "react-bootstrap/Form";
const AddRetailPaymentForm = () => {
  let itemNames;
  /* const itemNames = INVENTORY.map((item) => (
    <option key={item.id} value={item.id}>
      {item.part_name}
    </option>
  )); */
  itemNames.unshift(<option key={0} value={0}>Add items...</option>)  
  return (
    <Form>
            <Form.Group controlId="pay_date">
        <Form.Label>Payment Date</Form.Label>
        <Form.Control
          type="date"
          defaultValue={new Date().toISOString().substring(0, 10)}
        />
      </Form.Group>

      <Form.Group controlId="item">
        <Form.Label>Part Name</Form.Label>
        <Form.Select>
          {itemNames}
          </Form.Select>
      </Form.Group>
      <Form.Group controlId="qunatity">
        <Form.Label>Quantity</Form.Label>
        <Form.Control type="number" min="0" />
      </Form.Group>
      <Form.Group controlId="paymentType">
        <Form.Label>Payment Type</Form.Label>
        <Form.Select>
          <option value="card">Card</option>
          <option value="cash">Cash</option>
        </Form.Select>
      </Form.Group>
    </Form>
  );
};

export default AddRetailPaymentForm;
