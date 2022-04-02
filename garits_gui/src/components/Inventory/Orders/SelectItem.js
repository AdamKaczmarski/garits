import Form from "react-bootstrap/Form";
import Col from "react-bootstrap/Col";
import Row from "react-bootstrap/Row";

const SelectItem = (props) => {
    console.log(props)
  const itemHandler = (ev) => {
    props.item.partId = ev.target.value;
  };
  const quantityHandler = (ev) => {
    props.item.quantity = ev.target.value;
  };
  return (
    <Row>
      <Col>
        <Form.Label>{"Item "}</Form.Label>
        <Form.Select onChange={itemHandler}>{props.itemNames}</Form.Select>
      </Col>
      <Col>
        <Form.Label>Quantity</Form.Label>
        <Form.Control type="number" min={0} onChange={quantityHandler} />
      </Col>
    </Row>
  );
};

export default SelectItem;
