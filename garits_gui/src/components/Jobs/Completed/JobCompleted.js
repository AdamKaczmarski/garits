import Dropdown from "react-bootstrap/Dropdown";
import { useState } from "react";
import Button from "react-bootstrap/Button";
const Job = (props) => {
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);
  let bookingDate;
  if (props.job.bookingDate)
    bookingDate = new Date(props.job.bookingDate)
      .toISOString()
      .substring(0, 10);
  let fixDate;
  if (props.job.fixDate)
    fixDate = new Date(props.job.fixDate).toISOString().substring(0, 10);

  let bay = null;
  if (props.job.bay === "repair") {
    bay =
      props.job.bay.charAt(0).toUpperCase() +
      props.job.bay.slice(1).toLowerCase();
  } else bay = props.job.bay;
  return (
    <>
      <tr>
        <td>{props.job.customerName}</td>
        <td>{props.job.vehicle.idRegNo}</td>
        <td>{props.job.vehicle.manufacturer+" "+props.job.vehicle.model}</td>
        <td>{bay}</td>
        <td>
          {props.job.descriptionRequired &&
          props.job.descriptionRequired.length > 50
            ? props.job.descriptionRequired.substring(0, 47) + "..."
            : props.job.descriptionRequired}
        </td>
        <td>{props.job.estTimeMin + " mins"}</td>
        <td>
          {props.job.user[0].firstName + " " + props.job.user[0].lastName}
        </td>
        <td>{props.job.actTimeMin + " mins"}</td>
        <td>{bookingDate}</td>
        <td>{fixDate}</td>
        <td>
          <Dropdown className="m-0 p-0" align={"end"}>
            <Dropdown.Toggle variant="secondary">Action</Dropdown.Toggle>

            <Dropdown.Menu>
                <Dropdown.Item>Download report</Dropdown.Item>
              <Dropdown.Item onClick={handleShow}>Edit</Dropdown.Item>
              <Dropdown.Item
                style={{ backgroundColor: "rgba(242, 97, 99,0.2)" }}
                href="#/action-3"
              >
                Delete
              </Dropdown.Item>
            </Dropdown.Menu>
          </Dropdown></td><td>
          <Button>
            Details
          </Button>
        </td>
      </tr>
    </>
  );
};

export default Job;
