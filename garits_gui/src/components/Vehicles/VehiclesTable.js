import { useState, useEffect, useCallback } from "react";
import AddVehicleForm from "./AddVehicleForm";

import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";
import { Spinner } from "react-bootstrap";
import axios from "axios";
import AddVehicleModal from "./AddVehicleModal";
import Vehicle from "./Vehicle";

const VehiclesTable = (props) => {
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);
  const [isLoading, setIsLoading] = useState(true);
  const [vehicles, setVehicles] = useState([]);
  let newVehicle={};
  const addVehicle = async()=>{
    try {
      const response = await axios({
        method:"POST",
        url:`http://localhost:8080/vehicles/${props.customer_id}`,
        data:newVehicle
      });
      console.log(response);
    } catch (err) {
      console.log(err);
    } finally {
      obtainVehicles();
    }
  };
  const deleteVehicles = async (id) => {
    try {
      const response = await axios({
        method: "DELETE",
        url: `http://localhost:8080/vehicles/${props.customer_id}/${id}`,
      });
      console.log(response);
    } catch (err) {
      console.log(err);
    } finally {
      obtainVehicles();
    }
  };
  const obtainVehicles = useCallback(async () => {
    try {
      const response = await axios({
        method: "GET",
        url: `http://localhost:8080/vehicles/${props.customer_id}`,
      });
      setVehicles(response.data);
    } catch (err) {
      console.log(err);
    } finally {
      setIsLoading(false);
    }
  }, [props]);
  useEffect(() => {
    obtainVehicles();
  }, [obtainVehicles]);
  if (isLoading) {
    return <Spinner variant="primary" />;
  }
  let vehiclesView;
  if (vehicles && vehicles.length > 0) {
    vehiclesView = vehicles.map((vehicle) => (
      <Vehicle key={vehicle.idVehicle} vehicle={vehicle} deleteVehicles={deleteVehicles}/>
    ));
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
      <AddVehicleModal show={show} onClose={handleShow} addVehicle={addVehicle} title="Add Vehicle" submitAction={addVehicle} form={<AddVehicleForm />}/>
    </>
  );
};

export default VehiclesTable;
