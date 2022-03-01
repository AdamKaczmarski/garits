import { useState } from "react";

import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";

import AddVehicleModal from "./AddVehicleModal";
import Vehicle from "./Vehicle";

import { VEHICLES } from "../../dummy-data/vehicles";

const VehiclesTable = (props) => {
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);

  const vehiclesFiltered = VEHICLES.filter(
    (vehicle) => vehicle.customer_id === props.customer_id
  );
  const vehicles = vehiclesFiltered.map((vehicle) => (
    <Vehicle key={vehicle.id_reg_no} vehicle={vehicle} />
  ));

  return (
    <>
      <Table striped hover className="mt-3">
        <thead>
          <tr>
            <th style={{ width: "10%" }}>Registration Number</th>
            <th style={{ width: "10%" }}>Manufacturer</th>
            <th style={{ width: "10%" }}>Model</th>
            <th >Engine Number</th>
            <th >Chassis Number</th>
            <th style={{ width: "10%" }}>Colour</th>
            <th>Last MOT</th>
            <th>
              <Button variant="primary" onClick={handleShow}>
                +
              </Button>
            </th>
          </tr>
        </thead>
        <tbody>{vehicles}</tbody>
      </Table>
      <AddVehicleModal show={show} onClose={handleShow} />
    </>
  );
};

export default VehiclesTable;
