import { useState, useEffect, useCallback } from "react";
import axios from "axios";
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";

import AddVehicleModal from "./AddVehicleModal";
import Vehicle from "./Vehicle";

//import { VEHICLES } from "../../dummy-data/vehicles";
import { Spinner } from "react-bootstrap";

const VehiclesTable = (props) => {
  const [show, setShow] = useState(false);
  const [isLoading, setIsLoading] = useState(true);
  const [vehicles, setVehicles] = useState([]);
  const handleShow = () => setShow(!show);
  const obtainVehicles =  useCallback(async() => {
    try {
      const response = await axios({
        method: "GET",
        url: "http://localhost:8080/vehicles/"+props.customer_id,
      })
      setVehicles(response.data);
    } catch (err) {
      console.log(err);
    } finally {
      setIsLoading(false);
    }
  },[props.customer_id]);
  const removeVehicle = idRegNo =>{
    const newVehicles=vehicles.filter(v=>v.idRegNo!==idRegNo)
    setVehicles(newVehicles);
  }
  useEffect(() => {
    obtainVehicles();
  }, [obtainVehicles]);
  let vehiclesView;
  /*   const vehiclesFiltered = VEHICLES.filter(
    (vehicle) => vehicle.customer_id === props.customer_id
  );
 */ /*   const vehicles = vehiclesFiltered.map((vehicle) => (
    <Vehicle key={vehicle.id_reg_no} vehicle={vehicle} />
  ));
 */
  if (vehicles && vehicles.length > 0) {
    vehiclesView = vehicles.map((vehicle) => (
      <Vehicle key={vehicle.idRegNo} vehicle={vehicle} removeVehicle={removeVehicle}/>
    ));
  }
  if (isLoading){
    return <Spinner animation="border" variant="primary" />
  }
  return (
    <>
      <Table striped hover className="mt-3">
        <thead>
          <tr>
            <th style={{ width: "10%" }}>Registration Number</th>
            <th style={{ width: "10%" }}>Manufacturer</th>
            <th style={{ width: "10%" }}>Model</th>
            <th>Engine Number</th>
            <th>Chassis Number</th>
            <th style={{ width: "10%" }}>Colour</th>
            <th>Last MOT</th>
            <th>
              <Button variant="primary" onClick={handleShow}>
                +
              </Button>
            </th>
          </tr>
        </thead>
        <tbody>{vehiclesView}</tbody>
      </Table>
      <AddVehicleModal show={show} onClose={handleShow} />
    </>
  );
};

export default VehiclesTable;
