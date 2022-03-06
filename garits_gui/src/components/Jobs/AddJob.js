import Form from "react-bootstrap/Form";
import Button from'react-bootstrap/Button';
import { CUSTOMERS } from "../../dummy-data/customers";
import { VEHICLES } from "../../dummy-data/vehicles";
import {SERVICES} from '../../dummy-data/services';
const AddJob = () => {
  const customerNames = CUSTOMERS.map((customer) => (
    <option key={customer.id} value={customer.id}>
      {customer.name}
    </option>
  ));
  const registrations = VEHICLES.map((vehicle) => (
    <option key={vehicle.id_reg_no} value={vehicle.id_reg_no}>
      {vehicle.id_reg_no}
    </option>
  ));
  const services = SERVICES.map((service) => (
    <option key={service.id} value={service.id}>
      {service.service_name}
    </option>
  ));

  return (
    <Form>
      <Form.Group controlId="customer">
        <Form.Label>Customer</Form.Label>
        <Form.Select>{customerNames}</Form.Select>
      </Form.Group>
      <Form.Group controlId="vehicle">
          <Form.Label>Vehicle</Form.Label>
          <Form.Select>{registrations}</Form.Select>
        </Form.Group>
      <Form.Group controlId="service">
          <Form.Label>Service</Form.Label>
          <Form.Select>{services}</Form.Select>
        </Form.Group>
        <Button className="mt-3">Add service</Button>
        <Form.Group controlId="booking_date">
          <Form.Label>Booking date</Form.Label>
          <Form.Control
            type="date"
            defaultValue={new Date().toISOString().substring(0, 10)}
          />
        </Form.Group>

    </Form>
  );
};

export default AddJob;
