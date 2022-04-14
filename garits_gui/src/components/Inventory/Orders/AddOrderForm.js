import { useState, useEffect, useCallback, useRef, useContext } from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import axios from "axios";
import SelectItem from "./SelectItem";
import Spinner from "react-bootstrap/Spinner";
import AuthContext from "../../../store/auth-context";
const STATUSES = ["ordered", "completed"];

const AddOrderForm = (props) => {
  const [items, setItems] = useState([]);
  const [words, setWords] = useState(0);
  const [isLoading, setIsLoading] = useState(true);
  const authCtx = useContext(AuthContext);
  const options = STATUSES.map((status, index) => (
    <option value={status} key={index}>
      {status.charAt(0).toUpperCase() + status.slice(1).toLowerCase()}
    </option>
  ));
  let itemNames = useRef([]);
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
        props.orderItems.push({ partId: 0, quantity: 0 });
        setItems([
          <SelectItem
            itemNames={itemNames.current}
            key={Math.random()}
            item={props.orderItems[props.orderItems.length - 1]}
            from="order"
          />,
        ]);
      }
    } catch (err) {
      console.log(err);
    } finally {
      setIsLoading(false);
    }
  }, [props.orderItems]);
  useEffect(() => {
    obtainItemNames();
  }, [obtainItemNames]);
  if (isLoading) {
    return <Spinner animation="border" variant="primary" />;
  }
  const addItemSelect = () => {
    if (itemNames.current.length - 2 >= props.orderItems.length) {
      props.orderItems.push({ partId: 0, quantity: 0 });
      setItems([
        ...items,
        <SelectItem
          itemNames={itemNames.current}
          key={Math.random()}
          item={props.orderItems[props.orderItems.length - 1]}
          from="order"
        />,
      ]);
    } else {
      console.log("Cannot add any more items");
    }
  };

  const statusHandler = (ev) => {
    props.newOrder.status = ev.target.value;
  };
  const orderDateHandler = (ev) => {
    props.newOrder.orderDate = ev.target.value;
  };
  const arrivalDateHandler = (ev) => {
    props.newOrder.orderArrival = ev.target.value;
  };
  const descriptionHandler = (ev) => {
    props.newOrder.description = ev.target.value;
    setWords(ev.target.value.length);
  };
  const companyHandler = (ev) => {
    props.newOrder.company = ev.target.value;
  };
  const addressHandler = (ev) => {
    props.newOrder.address = ev.target.value;
  };
const telHandler = (ev) => {
  props.newOrder.phoneNo = ev.target.value;
};
const faxHandler = (ev) => {
  props.newOrder.fax = ev.target.value;
}
  return (
    <>
      <Form>
        <Form.Group controlId="status">
          <Form.Label>Status</Form.Label>
          <Form.Select onChange={statusHandler}>{options}</Form.Select>
        </Form.Group>
        <Form.Group controlId="order_date">
          <Form.Label>Order Date</Form.Label>
          <Form.Control
            type="date"
            defaultValue={new Date().toISOString().substring(0, 10)}
            onChange={orderDateHandler}
          />
        </Form.Group>
        <Form.Group controlId="order_arrival">
          <Form.Label>Arrival Date</Form.Label>
          <Form.Control type="date" onChange={arrivalDateHandler} />
        </Form.Group>
        <Form.Group conctrolId="company">
          <Form.Label>Company</Form.Label>
          <Form.Control onChange={companyHandler} />
        </Form.Group>
        <Form.Group conctrolId="address">
          <Form.Label>Address</Form.Label>
          <Form.Control onChange={addressHandler} />
        </Form.Group>
        <Form.Group conctrolId="telephone">
          <Form.Label>Telephone No.</Form.Label>
          <Form.Control onChange={telHandler} />
        </Form.Group>
        <Form.Group conctrolId="fax">
          <Form.Label>FAX</Form.Label>
          <Form.Control onChange={faxHandler} />
        </Form.Group>
        <Form.Group controlId="description">
          <Form.Label>Description</Form.Label>
          <Form.Control as="textarea" rows={2} onChange={descriptionHandler} />
          <span className="text-muted">{words}/200</span>
        </Form.Group>
        <Form.Group controlId="items">{items}</Form.Group>
        <Button className="mt-3" onClick={addItemSelect}>
          Add Item
        </Button>
      </Form>
    </>
  );
};

export default AddOrderForm;
