import Dropdown from "react-bootstrap/Dropdown";
import { useState } from "react";
import Button from "react-bootstrap/Button";
import CustomModal from "../../CommonComponents/CustomModal";
import JobDetails from "../JobDetails";
const JobCompleted = (props) => {
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);
  const [showDetails, setShowDetails] = useState(false);
  const handleShowDetails = () => setShowDetails(!showDetails);

  let bookingDate;
  if (props.job.bookingDate)
    bookingDate = new Date(props.job.bookingDate)
      .toISOString()
      .substring(0, 10);
  let fixDate;
  if (props.job.fixDate)
    fixDate = new Date(props.job.fixDate).toISOString().substring(0, 10);

  let bay = null;
  if (props.job.bay !== "MOT") {
    bay =
      props.job.bay.charAt(0).toUpperCase() +
      props.job.bay.slice(1).toLowerCase();
  } else bay = props.job.bay;

  return (
    <>
      <tr>
      <td>{props.job.idJob}</td>
        <td>{props.job.vehicle.customer[0].name}</td>
        <td>{props.job.vehicle.idRegNo}</td>
        <td>
          {props.job.vehicle.manufacturer + " " + props.job.vehicle.model}
        </td>
        <td>{bay}</td>
        <td>
          {props.job.descriptionDone && props.job.descriptionDone.length > 50
            ? props.job.descriptionDone.substring(0, 47) + "..."
            : props.job.descriptionDone}
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
              <Dropdown.Item
                style={{ backgroundColor: "rgba(242, 97, 99,0.2)" }}
                onClick={() => props.deleteJob(props.job.idJob)}
              >
                Delete
              </Dropdown.Item>
            </Dropdown.Menu>
          </Dropdown>
        </td>
        <td>
          <Button onClick={handleShowDetails}>Details</Button>
        </td>
      </tr>
      <CustomModal
        show={showDetails}
        onClose={handleShowDetails}
        title={`Detail for job ID: ${props.job.idJob}`}
        submitAction={null}
        form={<JobDetails idJob={props.job.idJob} />}
      />
    </>
  );
};

export default JobCompleted;
