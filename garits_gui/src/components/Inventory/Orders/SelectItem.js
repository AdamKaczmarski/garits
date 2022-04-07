import Form from "react-bootstrap/Form";
import Col from "react-bootstrap/Col";
import Row from "react-bootstrap/Row";

const SelectItem = (props) => {
  console.log(props);
  const itemHandler = (ev) => {
    if (props.item.partId) {
      props.item.partId = +ev.target.value;
    } else {
      props.item.idPart = +ev.target.value;
    }
  };
  const quantityHandler = (ev) => {
    if (props.item.quantity) {
      props.item.quantity = +ev.target.value;
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
        <Form.Control type="number" min={0} onChange={quantityHandler} />
      </Col>
    </Row>
  );
};

export default SelectItem;
