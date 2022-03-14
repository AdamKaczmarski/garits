import Dropdown from "react-bootstrap/Dropdown";

const Vehicle = (props) => {
  return (
    <tr>
      <td>{props.vehicle.id_reg_no}</td>
      <td>{props.vehicle.manufacturer}</td>
      <td>{props.vehicle.model}</td>
      <td>{props.vehicle.engine_serial_number}</td>
      <td>{props.vehicle.chassis_number}</td>
      <td>{props.vehicle.colour}</td>
      <td>{props.vehicle.last_mot}</td>
      <td>
        <Dropdown className="m-0 p-0" align={"end"}>
          <Dropdown.Toggle variant="secondary">Action</Dropdown.Toggle>

          <Dropdown.Menu>
            <Dropdown.Item>
              Edit
            </Dropdown.Item>
            <Dropdown.Item style={{ backgroundColor: "rgba(242, 97, 99,0.2)" }}>
              Delete
            </Dropdown.Item>
          </Dropdown.Menu>
        </Dropdown>
      </td>
    </tr>
  );
};

export default Vehicle;
