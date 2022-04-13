import Form from "react-bootstrap/Form";
import Col from "react-bootstrap/Col";
import Row from "react-bootstrap/Row";
import {useState} from 'react';
const SelectItem = (props) => {
  const [stockLevel, setstockLevel] = useState(0);
  const[isInvalid, setIsInvalid] = useState(false)
  const itemHandler = (ev) => {
    if (props.item.partId>=0) {
      if (props.from!=="order")
      {const item = props.items.find(item=>item.idPart===+ev.target.value);
      setstockLevel(item.stockLevel);}
      props.item.partId = +ev.target.value;
    } else {
      props.item.idPart = +ev.target.value;
    }
  };
  const quantityHandler = (ev) => {
    if (props.item.quantity>=0) {
      props.item.quantity = +ev.target.value;
      if (props.from!=="order")
      {if (+ev.target.value>stockLevel) {
        setIsInvalid(true);
      } else {
        setIsInvalid(false);
      }}
    } else {
      props.item.quantityUsed = +ev.target.value;
    }
  };
  return (
    <Row>
      <Col>
        <Form.Label>{"Item "}</Form.Label>
        <Form.Select onChange={itemHandler}>{props.itemNames}</Form.Select>
      </Col>
      <Col>
        <Form.Label>Quantity</Form.Label>
        <Form.Control type="number" min={0} isInvalid={isInvalid} onChange={quantityHandler} />
      </Col>
    </Row>
  );
};

export default SelectItem;
