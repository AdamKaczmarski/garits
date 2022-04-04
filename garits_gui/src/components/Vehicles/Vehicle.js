import Dropdown from "react-bootstrap/Dropdown";

const Vehicle = (props) => {
  const formattedMot = new Date(props.vehicle.lastMot).toISOString().substring(0,10);
  return (
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
            <Dropdown.Item>
              Edit
            </Dropdown.Item>
            <Dropdown.Item style={{ backgroundColor: "rgba(242, 97, 99,0.2)" }} onClick={
              ()=>{props.deleteVehicles(props.vehicle.idRegNo)}}>
              Delete
            </Dropdown.Item>
          </Dropdown.Menu>
        </Dropdown>
      </td>
    </tr>
  );
};

export default Vehicle;
