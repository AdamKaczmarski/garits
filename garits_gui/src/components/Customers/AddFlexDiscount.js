import Form from "react-bootstrap/Form";
const AddFlexDiscount = (props) => {
  const rangeHandler=(ev)=>{
    const newFlexDisc = props.flexDiscount;
    newFlexDisc.rangeFrom = +ev.target.value;

  }
  const amountHandler=(ev)=>{
    const newFlexDisc = props.flexDiscount;
    newFlexDisc.discount = +ev.target.value;

  }
  return (
    <Form>
      <Form.Group>
        <Form.Label>Range from</Form.Label>
        <Form.Control
          type="number"
          min={0}
          onChange={rangeHandler}
        />
      </Form.Group>{" "}
      <Form.Group>
        <Form.Label>Discount</Form.Label>
        <Form.Control
          type="number"
          min={0}
          onChange={amountHandler}
        />
      </Form.Group>
    </Form>
  );
};

export default AddFlexDiscount;
