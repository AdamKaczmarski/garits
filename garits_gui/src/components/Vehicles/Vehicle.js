import Dropdown from "react-bootstrap/Dropdown";
import CustomModal from "../CommonComponents/CustomModal";
import EditVehicleForm from "./EditVehicleForm";
import { useState } from "react";
const Vehicle = (props) => {
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);
  const formattedMot = new Date(props.vehicle.lastMot)
    .toISOString()
    .substring(0, 10);
  let editedVehicle = { ...props.vehicle };
  console.log(editedVehicle);
  return (
    <>
      <tr>
        <td>{props.vehicle.idRegNo}</td>
        <td>{props.vehicle.manufacturer}</td>
        <td>{props.vehicle.model}</td>
        <td>{props.vehicle.engineSerialNumber}</td>
        <td>{props.vehicle.chassisNumber}</td>
        <td>{props.vehicle.colour}</td>
        <td>{formattedMot}</td>
        <td>
          <Dropdown className="m-0 p-0" align={"end"}>
            <Dropdown.Toggle variant="secondary">Action</Dropdown.Toggle>

            <Dropdown.Menu>
              <Dropdown.Item onClick={handleShow}>Edit</Dropdown.Item>
              <Dropdown.Item
                style={{ backgroundColor: "rgba(242, 97, 99,0.2)" }}
                onClick={() => {
                  props.deleteVehicles(props.vehicle.idRegNo);
                }}
              >
                Delete
              </Dropdown.Item>
            </Dropdown.Menu>
          </Dropdown>
        </td>
      </tr>
      <CustomModal
        title={`Edit vehicle : ${props.vehicle.idRegNo}`}
        submitAction={() => props.editVehicle(editedVehicle)}
        show={show}
        onClose={handleShow}
        form={<EditVehicleForm editedVehicle={editedVehicle} />}
      />
    </>
  );
};

export default Vehicle;
