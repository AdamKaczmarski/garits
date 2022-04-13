const PartModal = (props) => {
  return (
    <>
      <tr>
        <td>{props.part.code}</td>
        <td>{props.part.partName}</td>
        <td>{props.part.partType}</td>
        <td>{props.part.manufacturer}</td>
        <td>{props.part.vehicleType}</td>
        <td>{props.part.yearS}</td>
        <td style={{ backgroundColor: "rgba(242, 97, 99,0.2)" }}><span style={{'color':'red'}}>{props.part.stockLevel}</span></td>
        <td><span style={{fontWeight:'bold'}}>{props.part.stockLevelThreshold}</span></td>
      </tr>
    </>
  );
};

export default PartModal;
