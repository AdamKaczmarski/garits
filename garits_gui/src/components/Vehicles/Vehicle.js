import Dropdown from "react-bootstrap/Dropdown";
import axios from 'axios';
const Vehicle = (props) => {
  let formattedDate =new Date(props.vehicle.lastMot).toISOString().substring(0,10);
  const deleteVehicle = () =>{
    try {
      const response = axios({
        method:"DELETE",
        url:"http://localhost:8080/vehicles/"+props.vehicle.idRegNo
      });
      console.log(response);
    } catch (err ){
      console.log(err);
    } finally {
      props.removeVehicle(props.vehicle.idRegNo)
    }
  }
  return (
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
            <Dropdown.Item>
              Edit
            </Dropdown.Item>
            <Dropdown.Item style={{ backgroundColor: "rgba(242, 97, 99,0.2)" }} onClick={deleteVehicle}>
              Delete
            </Dropdown.Item>
          </Dropdown.Menu>
        </Dropdown>
      </td>
    </tr>
  );
};

export default Vehicle;
