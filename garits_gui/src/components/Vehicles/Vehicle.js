import Dropdown from "react-bootstrap/Dropdown";
import axios from "axios";
import VehicleModal from "./VehicleModal";
import EditVehicleForm from "./EditVehicleForm";
import { useState } from "react";
const Vehicle = (props) => {
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);

  let formattedDate = new Date(props.vehicle.lastMot)
    .toISOString()
    .substring(0, 10);
  const deleteVehicle = () => {
    try {
      const response = axios({
        method: "DELETE",
        url: "http://localhost:8080/vehicles/" + props.vehicle.idRegNo,
      });
      console.log(response);
    } catch (err) {
      console.log(err);
    } finally {
      props.removeVehicle(props.vehicle.idRegNo);
    }
  };
  const editedVehicle = { ...props.vehicle };
  const editVehicle = async () => {
    try {
      const response = await axios({
        method: "PATCH",
        url: "http://localhost:8080/vehicles/" + props.vehicle.idRegNo,
        data: editedVehicle,
      });
      console.log(response.status);
    } catch (err) {
      console.log();
    } finally {
      const newVehicles = props.vehicles.filter(
        (v) => v.idRegNo !== props.vehicle.idRegNo
      );
      newVehicles.push(editedVehicle);
      props.setVehicles(newVehicles);
    }
  };
  return (
    <>
      <tr>
        <td>{props.vehicle.idRegNo}</td>
        <td>{props.vehicle.manufacturer}</td>
        <td>{props.vehicle.model}</td>
        <td>{props.vehicle.engineSerialNumber}</td>
        <td>{props.vehicle.chassisNumber}</td>
        <td>{props.vehicle.colour}</td>
        <td>{formattedDate}</td>
        <td>
          <Dropdown className="m-0 p-0" align={"end"}>
            <Dropdown.Toggle variant="secondary">Action</Dropdown.Toggle>

            <Dropdown.Menu>
              <Dropdown.Item onClick={handleShow}>Edit</Dropdown.Item>
              <Dropdown.Item
                style={{ backgroundColor: "rgba(242, 97, 99,0.2)" }}
                onClick={deleteVehicle}
              >
                Delete
              </Dropdown.Item>
            </Dropdown.Menu>
          </Dropdown>
        </td>
      </tr>
      <VehicleModal
        show={show}
        onClose={handleShow}
        title={"Edit " + props.vehicle.idRegNo}
        submitAction={editVehicle}
        form={<EditVehicleForm vehicle={editedVehicle} />}
      />
    </>
  );
};

export default Vehicle;
