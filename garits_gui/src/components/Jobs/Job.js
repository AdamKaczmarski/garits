import Dropdown from "react-bootstrap/Dropdown";
import { useState } from "react";
const Job = (props) => {
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);

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
        <td>{props.job.reg_no_id}</td>
        <td>{props.job.car}</td>
        {props.jobType === "active" || props.jobType === "completed" ? (
          <td>{bay}</td>
        ) : null}

        <td>
          {props.job.description_required.length > 50
            ? props.job.description_required.substring(0, 47) + "..."
            : props.job.description_required}
        </td>
        <td>{props.job.est_time_min + " mins"}</td>
        {props.jobType === "active" || props.jobType === "completed" ? (
          <td>{props.job.assignedUser}</td>
        ) : null}
        {props.jobType === "active" || props.jobType === "completed" ? (
          <td>
            {props.job.description_done.length > 70
              ? props.job.description_done.substring(0, 67) + "..."
              : props.job.description_done}
          </td>
        ) : null}
        {props.jobType === "active" || props.jobType === "completed" ? (
          <td>{props.job.act_time_min + " mins"}</td>
        ) : null}
        {props.jobType === "booked" ? (
          <td>{props.job.created_date}</td>
        ) : null}
        <td>{props.job.booking_date}</td>
        {props.jobType === "completed" ? <td>{props.job.fix_date}</td> : null}
        <td colSpan={2}>
          <Dropdown className="m-0 p-0" align={"end"}>
            <Dropdown.Toggle variant="secondary">Action</Dropdown.Toggle>

            <Dropdown.Menu>
              <Dropdown.Item onClick={handleShow}>Edit</Dropdown.Item>
              <Dropdown.Item
                style={{ backgroundColor: "rgba(242, 97, 99,0.2)" }}
                href="#/action-3"
              >
                Delete
              </Dropdown.Item>
            </Dropdown.Menu>
          </Dropdown>
        </td>
      </tr>
    </>
  );
};

export default Job;
