
import Form from "react-bootstrap/Form";
//import { SERVICES } from "../../dummy-data/services";
import Accordion from "react-bootstrap/Accordion";
import Button from "react-bootstrap/Button";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import axios from "axios";
import ListGroup from "react-bootstrap/ListGroup";

const EditCustomer = (props) => {
  const emailHandler = (ev) => {
    const newCustomer = props.customer;
    newCustomer.email = ev.target.value;
    props.setCustomer(newCustomer);
  };
  const nameHandler = (ev) => {
    const newCustomer = props.customer;
    newCustomer.name = ev.target.value;
    props.setCustomer(newCustomer);
  };
  const addressHandler = (ev) => {
    const newCustomer = props.customer;
    newCustomer.address = ev.target.value;
    props.setCustomer(newCustomer);
  };
  const postcodeHandler = (ev) => {
    const newCustomer = props.customer;
    newCustomer.postcode = ev.target.value;
    props.setCustomer(newCustomer);
  };
  const telephoneNumberHandler = (ev) => {
    const newCustomer = props.customer;
    newCustomer.telephoneNumber = ev.target.value;
    props.setCustomer(newCustomer);
  };
  const faxHandler = (ev) => {
    const newCustomer = props.customer;
    newCustomer.fax = ev.target.value;
    props.setCustomer(newCustomer);
  };
  const fixedDiscountHandler = (ev) => {
    const newCustomer = props.customer;
    newCustomer.fixedDiscount = ev.target.value;
    props.setCustomer(newCustomer);
  };
  const deleteDiscount = (idVarDiscount, idFlexDiscount) => {
    console.log(idVarDiscount);
    try {
      let response;
      if (idVarDiscount != null) {
        response = axios({
          method: "DELETE",
          url:
            "http://localhost:8080/customers/" +
            props.customer.idCustomer +
            "/varDiscount/" +
            idVarDiscount,
        });
      } else {
        response = axios({
          method: "DELETE",
          url:
            "http://localhost:8080/customers/" +
            props.customer.idCustomer +
            "/flexDiscount/" +
            idFlexDiscount,
        });
      }
      console.log(response);
    } catch (err) {
    } finally {
      /* if (idVarDiscount != null)
        serviceDiscounts.filter((vD) => vD.idVarDiscount !== idVarDiscount);
      if (idFlexDiscount != null)
        flexDiscounts.filter((fD) => fD.idFlexDiscount !== idFlexDiscount); */
        //window.location.reload();
    }
  };

  const flexDiscounts = props.customer.flexDiscounts.map((discount) => (
    <ListGroup.Item key={discount.idFlexDiscount}>
      <Row className="justify-content-between">
        <Col>
          <span>
            From {discount.rangeFrom}: {discount.discount}%
          </span>
        </Col>
        <Col className="d-flex flex-row-reverse">
          <Button
            variant="danger"
            onClick={() => deleteDiscount(null, discount.idFlexDiscount)}
          >
            Delete
          </Button>{" "}
        </Col>
      </Row>
    </ListGroup.Item>
  ));
  const serviceDiscounts = props.customer.variableDiscounts.map((discount) => {
    return (
      <ListGroup.Item key={discount.idVarDiscount}>
        <Row className="justify-content-between">
          <Col>
            <span>
              {discount.serviceId}: {discount.discount}%
            </span>
          </Col>
          <Col className="d-flex flex-row-reverse">
            <Button
              variant="danger"
              onClick={() => deleteDiscount(discount.idVarDiscount, null)}
            >
              Delete
            </Button>
          </Col>
        </Row>
      </ListGroup.Item>
    );
  });

  return (
    <>
      <Form>
        <Form.Group>
          <Form.Label>Name</Form.Label>
          <Form.Control
            defaultValue={props.customer.name}
            onChange={emailHandler}
          />
        </Form.Group>
        <Form.Group>
          <Form.Label>Email</Form.Label>
          <Form.Control
            defaultValue={props.customer.email}
            onChange={nameHandler}
          />
        </Form.Group>
        <Form.Group>
          <Form.Label>Address</Form.Label>
          <Form.Control
            defaultValue={props.customer.address}
            onChange={addressHandler}
          />
        </Form.Group>
        <Form.Group>
          <Form.Label>Postcode</Form.Label>
          <Form.Control
            defaultValue={props.customer.postcode}
            onChange={postcodeHandler}
          />
        </Form.Group>
        <Form.Group>
          <Form.Label>Telephone number</Form.Label>
          <Form.Control
            defaultValue={props.customer.telephoneNumber}
            onChange={telephoneNumberHandler}
          />
        </Form.Group>
        <Form.Group>
          <Form.Label>FAX</Form.Label>
          <Form.Control
            defaultValue={props.customer.fax}
            onChange={faxHandler}
          />
        </Form.Group>
        <Form.Group>
          <Form.Label>Fixed discount</Form.Label>
          <Form.Control
            defaultValue={props.customer.fixedDiscount}
            onChange={fixedDiscountHandler}
            min={0}
            max={100}
          />
        </Form.Group>
      </Form>
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
    </>
  );
};

export default EditCustomer;
