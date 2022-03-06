import Dropdown from "react-bootstrap/Dropdown";
const PartModal = (props) => {
  return (
    <>
      <tr>
        <td>{props.part.code}</td>
        <td>{props.part.part_name}</td>
        <td>{props.part.part_type}</td>
        <td>{props.part.manufacturer}</td>
        <td>{props.part.vehicle_type}</td>
        <td>{props.part.year_s}</td>
        <td style={{ backgroundColor: "rgba(242, 97, 99,0.2)" }}><span style={{'color':'red'}}>{props.part.stock_level}</span></td>
        <td><span style={{fontWeight:'bold'}}>{props.part.stock_level_threshold}</span></td>
      </tr>
    </>
  );
};

export default PartModal;
