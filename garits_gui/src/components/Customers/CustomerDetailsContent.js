import ListGroup from "react-bootstrap/ListGroup";
import Accordion from "react-bootstrap/Accordion";
const BoldSpan = (props) => {
  return <span style={{ fontWeight: "bold" }}>{props.children}</span>;
};
const CustomerDetailsContent = (props) => {
  let flexDiscounts, serviceDiscounts;
  if (props.customer.flexDiscounts && props.customer.flexDiscounts.length > 0) {
    flexDiscounts = props.customer.flexDiscounts.map((discount) => (
      <ListGroup.Item key={discount.idFlexDiscount}>
        <span>
          From {discount.rangeFrom}: {discount.discount}%
        </span>
      </ListGroup.Item>
    ));
  }
  if (
    props.customer.variableDiscounts &&
    props.customer.variableDiscounts.length > 0
  ) {
    serviceDiscounts = props.customer.variableDiscounts.map(
      (discount) => {
        return (
          <ListGroup.Item key={discount.idVarDiscount}>
            <span>
              {discount.serviceId}: {discount.discount}%
            </span>
          </ListGroup.Item>
        );
      }
    );
  }
  return (
    <ListGroup>
      <ListGroup.Item>
        <BoldSpan>ID</BoldSpan>: {props.customer.idCustomer}
      </ListGroup.Item>
      <ListGroup.Item>
        <BoldSpan>Name</BoldSpan>: {props.customer.name}
      </ListGroup.Item>
      <ListGroup.Item>
        <BoldSpan>Email</BoldSpan>: {props.customer.email}
      </ListGroup.Item>
      <ListGroup.Item>
        <BoldSpan>Address</BoldSpan>: {props.customer.address}
      </ListGroup.Item>
      <ListGroup.Item>
        <BoldSpan>Postcode</BoldSpan>: {props.customer.postcode}
      </ListGroup.Item>
      <ListGroup.Item>
        <BoldSpan>Telephone number</BoldSpan>: {props.customer.telephoneNumber}
      </ListGroup.Item>
      <ListGroup.Item>
        <BoldSpan>FAX</BoldSpan>:{" "}
        {props.customer.fax === null ? "N/A" : props.customer.fax}
      </ListGroup.Item>
      <ListGroup.Item>
        <BoldSpan>Fixed discount</BoldSpan>: {props.customer.fixedDiscount}
      </ListGroup.Item>
      <ListGroup.Item>
        <Accordion>
          <Accordion.Item eventKey={0}>
            <Accordion.Header>Flexible discounts</Accordion.Header>
            <Accordion.Body>{flexDiscounts}</Accordion.Body>
          </Accordion.Item>
          <Accordion.Item eventKey={1}>
            <Accordion.Header>Service discounts</Accordion.Header>
            <Accordion.Body>{serviceDiscounts}</Accordion.Body>
          </Accordion.Item>
        </Accordion>
      </ListGroup.Item>
    </ListGroup>
  );
};
export default CustomerDetailsContent;
