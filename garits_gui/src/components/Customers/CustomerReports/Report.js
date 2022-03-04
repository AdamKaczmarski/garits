const Report = (props) => {
  return (
    <tr>
      <td>{props.report.date}</td>
      <td>{props.report.name}</td>
      <td>
        {props.report.type.charAt(0).toUpperCase() +
          props.report.type.slice(1).toLowerCase()}
      </td>
      <td>
        <a href="#">Download</a>
      </td>
    </tr>
  );
};

export default Report;
