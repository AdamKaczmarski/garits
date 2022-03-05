import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import { INVENTORY } from "../../../dummy-data/inventory";

const STATUSES = ["ordered", "completed"];

const AddOrderForm = () => {
  const options = STATUSES.map((status, index) => (
    <option value={status} key={index}>
      {status.charAt(0).toUpperCase() + status.slice(1).toLowerCase()}
    </option>
  ));
  const itemNames = INVENTORY.map((item) => (
    <option key={item.id} value={item.id}>
      {item.part_name}
    </option>
  ));
  itemNames.unshift(<option key={0} value={0}>Add items...</option>)

  /* 
    Export the Form.Group for item selection to another components, leave like this now for just gui things
  */
  return (
    <>
      <Form>
        <Form>
          <Form.Group controlId="status">
            <Form.Label>Status</Form.Label>
            <Form.Select>{options}</Form.Select>
          </Form.Group>
        </Form>
        <Form.Group controlId="order_date">
          <Form.Label>Order Date</Form.Label>
          <Form.Control
            type="date"
            defaultValue={new Date().toISOString().substring(0, 10)}
          />
        </Form.Group>
        <Form.Group controlId="description">
          <Form.Label>Description</Form.Label>
          <Form.Control as="textarea" rows={2} />
        </Form.Group>
        <Form.Group>
          <Form.Label>{"Item "}</Form.Label>
          <Form.Select>{itemNames}</Form.Select>
        </Form.Group>
        <Button className="mt-3">Add Item</Button>
      </Form>
    </>
  );
};

export default AddOrderForm;
