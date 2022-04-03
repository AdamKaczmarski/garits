import { useState, useEffect, useCallback, useRef } from "react";
import axios from "axios";
import Form from "react-bootstrap/Form";
import { Spinner } from "react-bootstrap";
import SelectItem from '../../Inventory/Orders/SelectItem';
const AddRetailPaymentForm = (props) => {
  const [items, setItems] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  let itemNames = useRef([]);
  const obtainItemNames = useCallback(async () => {
    try {
      const response = await axios({
        method: "GET",
        url: "http://localhost:8080/partNames",
      });
      console.log(response);
      if (response.status === 200) {
        itemNames.current = response.data.map((item) => (
          <option key={item.idPart} value={item.idPart}>
            {item.partName}
          </option>
        ));
        itemNames.current.unshift(
          <option key={0} value={0}>
            Add items...
          </option>
        );

        console.log(itemNames);
        props.paymentRetailItems.push({ partId: 0, quantity: 0 });
        setItems([
          <SelectItem
            itemNames={itemNames.current}
            key={Math.random()}
            item={props.paymentRetailItems[props.paymentRetailItems.length - 1]}
          />,
        ]);
      }
    } catch (err) {
      console.log(err);
    } finally {
      setIsLoading(false);
    }
  }, [props.paymentRetailItems]);
  useEffect(() => {
    obtainItemNames();
  }, [obtainItemNames]);
  if (isLoading) {
    return <Spinner animation="border" variant="primary" />;
  }
  const addItemSelect = () => {
    if (itemNames.current.length - 2 >= props.paymentRetailItems.length) {
      props.paymentRetailItems.push({ partId: 0, quantity: 0 });
      setItems([
        ...items,
        <SelectItem
          itemNames={itemNames.current}
          key={Math.random()}
          item={props.paymentRetailItems[props.paymentRetailItems.length - 1]}
        />,
      ]);
    } else {
      console.log("Cannot add any more items");
    }
  };
  return (
    <Form>
      <Form.Group controlId="pay_date">
        <Form.Label>Payment Date</Form.Label>
        <Form.Control
          type="date"
          defaultValue={new Date().toISOString().substring(0, 10)}
        />
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
