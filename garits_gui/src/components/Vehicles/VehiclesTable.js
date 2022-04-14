import { useState, useEffect, useCallback, useContext } from "react";
import AddVehicleForm from "./AddVehicleForm";

import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";
import { Spinner } from "react-bootstrap";
import axios from "axios";
import AuthContext from "../../store/auth-context";
import Vehicle from "./Vehicle";
import CustomModal from "../CommonComponents/CustomModal";

const VehiclesTable = (props) => {
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);
  const [isLoading, setIsLoading] = useState(true);
  const [vehicles, setVehicles] = useState([]);
  const authCtx = useContext(AuthContext);
  let newVehicle = {
    idRegNo: "",
    manufacturer: "",
    model: "",
    engineSerialNumber: "",
    chassisNumber: "",
    colour: "",
    lastMot: "",
  };
  const editVehicle = async (editedVehicle) => {
    try {
      setIsLoading(true);
      const response = await axios({
        method: "PATCH",
        url: `http://localhost:8080/vehicles/${editedVehicle.idVehicle}`,
        data: editedVehicle,
        headers: { Authorization: `Bearer ${authCtx.authData.token}` },
      });
      console.log(response);
    } catch (err) {
      console.log(err);
    } finally {
      handleShow();
      obtainVehicles();
    }
  };
  const addVehicle = async () => {
    try {
      setIsLoading(true);
      const response = await axios({
        method: "POST",
        url: `http://localhost:8080/vehicles/${props.customer_id}`,
        data: newVehicle,
        headers: { Authorization: `Bearer ${authCtx.authData.token}` },
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
        headers: { Authorization: `Bearer ${authCtx.authData.token}` },
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
        headers: { Authorization: `Bearer ${authCtx.authData.token}` },
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
      <Vehicle
        key={vehicle.idVehicle}
        vehicle={vehicle}
        deleteVehicles={deleteVehicles}
        editVehicle={editVehicle}
      />
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
      <CustomModal
        show={show}
        onClose={handleShow}
        addVehicle={addVehicle}
        title="Add Vehicle"
        submitAction={addVehicle}
        form={<AddVehicleForm newVehicle={newVehicle} />}
      />
    </>
  );
};

export default VehiclesTable;
