import { useState } from "react";
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";
import { SERVICES } from "../../dummy-data/services";
import Service from "./Service";
import AddServiceForm from "./AddServiceForm";
import ServiceModal from "./ServiceModal";
const ServicesTable = () => {
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);
  const services = SERVICES.map((service) => <Service service={service} />);
  return (
    <>
      <Table striped hover className="mt-3">
        <thead>
          <tr>
            <th>Name</th>
            <th>Price</th>
            <th>Approx. time</th>
            <th>Short description</th>
            <th
              style={{ display: "flex" }}
              className="justify-content-between  "
            >
              Actions
              <Button variant="outline-primary" onClick={handleShow}>
                +
              </Button>
            </th>
          </tr>
        </thead>
        <tbody>{services}</tbody>
      </Table>
      <ServiceModal show={show} onClose={handleShow} title="Add service" form={<AddServiceForm />} />
    </>
  );
};

export default ServicesTable;
