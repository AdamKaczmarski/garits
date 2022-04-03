import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import Accordion from "react-bootstrap/Accordion";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import { SERVICES } from "../../dummy-data/services";
const EditCustomer = (props) => {
  const services = SERVICES.map((service) => (
    <option key={service.id} value={service.id}>
      {service.service_name}
    </option>
  ));
  return (
    <Form>
      <Form.Group>
        <Form.Label>Name</Form.Label>
        <Form.Control value={props.customer.name} />
      </Form.Group>
      <Form.Group>
        <Form.Label>Email</Form.Label>
        <Form.Control value={props.customer.email} />
      </Form.Group>
      <Form.Group>
        <Form.Label>Address</Form.Label>
        <Form.Control value={props.customer.address} />
      </Form.Group>
      <Form.Group>
        <Form.Label>Postcode</Form.Label>
        <Form.Control value={props.customer.postcode} />
      </Form.Group>
      <Form.Group>
        <Form.Label>Telephone number</Form.Label>
        <Form.Control value={props.customer.telephone_number} />
      </Form.Group>
      <Form.Group>
        <Form.Label>FAX</Form.Label>
        <Form.Control value={props.customer.fax} />
      </Form.Group>
      <Form.Group>
        <Form.Label>Fixed discount</Form.Label>
        <Form.Control value={props.customer.fixed_discount} />
      </Form.Group>
      <Accordion className="mt-3">
        <Accordion.Item eventKey={0}>
          <Accordion.Header>Flexible discounts</Accordion.Header>
          <Accordion.Body>
            <Row>
              <Col>
                <Form.Group>
                  {" "}
                  <Form.Label>Range from</Form.Label>
                  <Form.Control value={props.customer.fixed_discount} />
                </Form.Group>
              </Col>
              <Col>
                {" "}
                <Form.Group>
                  <Form.Label>Amount</Form.Label>
                  <Form.Control value={props.customer.fixed_discount} />
                </Form.Group>
              </Col>
            </Row>

            <Button className="mt-3">Add range</Button>
          </Accordion.Body>
        </Accordion.Item>
        <Accordion.Item eventKey={1}>
          <Accordion.Header>Service discounts</Accordion.Header>
          <Accordion.Body>
            <Row>
              <Col>
                <Form.Group>
                  <Form.Label>Service</Form.Label>
                  <Form.Select>{services}</Form.Select>
                </Form.Group>
              </Col>
              <Col>
                <Form.Group>
                  <Form.Label>Discount</Form.Label>
                  <Form.Control type="number" value={15} />
                </Form.Group>
              </Col>
            </Row>
          </Accordion.Body>
        </Accordion.Item>
      </Accordion>
    </Form>
  );
};

export default EditCustomer;
