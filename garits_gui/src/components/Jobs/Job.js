import Dropdown from "react-bootstrap/Dropdown";
import { useState, useRef } from "react";
import Button from "react-bootstrap/Button";
import CustomModal from "../CommonComponents/CustomModal";
import JobDetails from "./JobDetails";
import UserAssignment from "./Actions/UserAssignment";
import CompleteJob from "./Actions/CompleteJob";
import EditDescriptionRequired from "./Actions/EditDescriptionRequired";
import EditUsedParts from "./Actions/EditUsedParts";
import EditServicesForJob from "./Actions/EditServicesForJob";
import axios from "axios";
const Job = (props) => {
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);
  const [showDetails, setShowDetails] = useState(false);
  const handleShowDetails = () => setShowDetails(!showDetails);
  const [form, setForm] = useState();
  const submitAction = useRef();
  let bookingDate;
  if (props.job.bookingDate)
    bookingDate = new Date(props.job.bookingDate)
      .toISOString()
      .substring(0, 10);
  let fixDate;
  if (props.job.fixDate)
    fixDate = new Date(props.job.fixDate).toISOString().substring(0, 10);
  let createdDate;
  if (props.job.createDate)
    createdDate = new Date(props.job.createDate).toISOString().substring(0, 10);
  let bay = null;
  if (props.job.bay && props.job.bay !== "MOT") {
    bay =
      props.job.bay.charAt(0).toUpperCase() +
      props.job.bay.slice(1).toLowerCase();
  } else bay = props.job.bay;
  let formData;
  const formHandler = (id) => {
    switch (id) {
      case 1: {
        formData = { idUser: 0, bay: "" };
        submitAction.current = async () => {
          try {
            const editedJob = { ...props.job, bay: formData.bay };
            editedJob.user[0].idUser = formData.idUser;
            const response = axios({
              method: "PATCH",
              url: `http://localhost:8080/jobs/${props.job.idJob}`,
              data: editedJob,
            });
            console.log(response);
          } catch (err) {
            console.log(err);
          } finally {
            props.obtainJobs();
          }
        };

        setForm(
          <UserAssignment
            user={props.job.user}
            bay={props.job.bay}
            formData={formData}
          />
        );
        break;
      }
      case 2: {
        setForm(<CompleteJob />);
        break;
      }
      case 3: {
        setForm(<EditDescriptionRequired />);
        break;
      }
      case 4: {
        setForm(<EditUsedParts idJob={props.job.idJob} />);
        break;
      }
      case 5: {
        setForm(<EditServicesForJob />);
        break;
      }
      default: {
        setForm(<UserAssignment />);
        break;
      }
    }
    handleShow();
  };
  return (
    <>
      <tr>
        <td>{props.job.vehicle.customer[0].name}</td>
        <td>{props.job.vehicle.idRegNo}</td>
        <td>
          {props.job.vehicle.manufacturer + " " + props.job.vehicle.model}
        </td>
        {props.jobType === "active" || props.jobType === "completed" ? (
          <td>{bay}</td>
        ) : null}

        <td>
          {props.job.descriptionRequired &&
          props.job.descriptionRequired.length > 50
            ? props.job.descriptionRequired.substring(0, 47) + "..."
            : props.job.descriptionRequired}
        </td>
        <td>{props.job.estTimeMin + " mins"}</td>
        {props.jobType === "active" || props.jobType === "completed" ? (
          <td>
            {" "}
            {props.job.user.length > 0
              ? props.job.user[0].firstName + " " + props.job.user[0].lastName
              : null}
          </td>
        ) : null}
        {props.jobType === "completed" ? (
          <td>
            {props.job.descriptionDone && props.job.descriptionDone.length > 70
              ? props.job.descriptionDone.substring(0, 67) + "..."
              : props.job.descriptionDone}
          </td>
        ) : null}
        {props.jobType === "completed" ? (
          <td>{props.job.actTimeMin + " mins"}</td>
        ) : null}
        {props.jobType === "booked" ? <td>{createdDate}</td> : null}
        <td>{bookingDate}</td>
        {props.jobType === "completed" ? <td>{fixDate}</td> : null}
        <td>
          <Dropdown className="m-0 p-0" align={"end"}>
            <Dropdown.Toggle variant="secondary">Action</Dropdown.Toggle>

            <Dropdown.Menu>
              {props.jobType === "active" ? (
                <>
                  <Dropdown.Item onClick={() => formHandler(2)}>
                    Set completed
                  </Dropdown.Item>
                  <Dropdown.Item onClick={() => formHandler(4)}>
                    {props.job.parts && props.job.parts.length > 0
                      ? "Edit"
                      : "Set"}{" "}
                    used parts
                  </Dropdown.Item>
                  <Dropdown.Item onClick={() => formHandler(5)}>
                    Edit services
                  </Dropdown.Item>
                  <Dropdown.Item onClick={() => formHandler(1)}>
                    Change assignment
                  </Dropdown.Item>
                </>
              ) : null}
              {props.jobType === "booked" ? (
                <>
                  <Dropdown.Item onClick={() => formHandler(1)}>
                    Assign mechanic
                  </Dropdown.Item>
                  <Dropdown.Item onClick={() => formHandler(3)}>
                    Change description
                  </Dropdown.Item>
                  <Dropdown.Item onClick={() => formHandler(5)}>
                    Change services
                  </Dropdown.Item>
                </>
              ) : null}
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
      <CustomModal
        show={show}
        onClose={handleShow}
        title={`Action on job ID: ${props.job.idJob}`}
        submitAction={submitAction.current}
        form={form}
      />
    </>
  );
};

export default Job;
