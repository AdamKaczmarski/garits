import { useState, useEffect, useCallback, useRef, useContext } from "react";
import axios from "axios";
import Form from "react-bootstrap/Form";
import { Spinner } from "react-bootstrap";
import SelectItem from '../../Inventory/Orders/SelectItem';
import Button from 'react-bootstrap/Button';
import AuthContext from "../../../store/auth-context";
const AddRetailPaymentForm = (props) => {
  const [items, setItems] = useState([]);
  const [total, setTotal] = useState(0);
  const [isLoading, setIsLoading] = useState(true);
  let itemNames = useRef([]);
  let itemsTmp = useRef();
const authCtx = useContext(AuthContext)
  const obtainItemNames = useCallback(async () => {
    try {
      const response = await axios({
        method: "GET",
        url: "http://localhost:8080/partNames",
        headers:{'Authorization': `Bearer ${authCtx.authData.token}`}
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
        itemsTmp.current=response.data;
        console.log(itemNames);
        props.paymentRetailItems.push({ partId: 0, quantity: 0 });
        setItems([
          <SelectItem
            itemNames={itemNames.current}
            key={Math.random()}
            item={props.paymentRetailItems[props.paymentRetailItems.length - 1]}
            items={itemsTmp.current}
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
          items={itemsTmp.current}
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
      <Form.Group controlId="items">
        {items}
        <Button className="mt-3"onClick={addItemSelect}>
          Add item
        </Button>
      </Form.Group>
      <Form.Group controlId="total">
      <Form.Label><span style={{fontWeight:'bold', fontSize:"1.5em"}}>Total: {total}</span></Form.Label>
      </Form.Group>
    </Form>
  );
};

export default AddRetailPaymentForm;
