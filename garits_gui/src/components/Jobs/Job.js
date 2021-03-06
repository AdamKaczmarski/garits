import Dropdown from "react-bootstrap/Dropdown";
import { useState, useRef, useContext } from "react";
import Button from "react-bootstrap/Button";
import CustomModal from "../CommonComponents/CustomModal";
import JobDetails from "./JobDetails";
import UserAssignment from "./Actions/UserAssignment";
import CompleteJob from "./Actions/CompleteJob";
import EditDescriptionRequired from "./Actions/EditDescriptionRequired";
import axios from "axios";
import AuthContext from "../../store/auth-context";
const Job = (props) => {
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);
  const [showDetails, setShowDetails] = useState(false);
  const handleShowDetails = () => setShowDetails(!showDetails);
  const [form, setForm] = useState();
  const submitAction = useRef();
  const authCtx = useContext(AuthContext);
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
        formData = {
          user: [{ idUser: 0 }],
          bay: "",
          status: "active",
          bookingDate:"",
        };
        console.log(props.job.status);
        //console.log(props.job.status === "active" ? "active" : "booked")
        submitAction.current = async () => {
          console.log(formData);
          try {
            const editedJob = {
              ...props.job,
              bay: formData.bay,
              status: formData.status,
              user: [formData.user[0]],
              bookingDate: formData.bookingDate,
            };
            const response = await axios({
              method: "PATCH",
              url: `http://localhost:8080/jobs/${props.job.idJob}`,
              data: editedJob,
              headers: { Authorization: `Bearer ${authCtx.authData.token}` },
            });
            console.log(response);
          } catch (err) {
            console.log(err);
          } finally {
            if (props.job.bay === "" || !props.job.bay) {
              window.location.reload();
            } else {
              props.obtainJobs();
              setShow(false);
            }
          }
        };

        setForm(
          <UserAssignment
            user={props.job.status === "active" ? props.job.user : null}
            bay={props.job.status === "active" ? props.job.bay : null}
            formData={formData}
          />
        );
        break;
      }
      case 2: {
        formData = {
          parts: [],
          descriptionDone: props.job.descriptionRequired,
          actTimeMin: props.job.estTimeMin,
        };
        submitAction.current = async () => {
          console.log(formData);
          try {
            formData.parts = formData.parts.filter(
              (p) => p.idPart !== 0 && p.quantityUsed > 0
            );
            console.log(formData.parts);
            const editedJob = {
              ...props.job,
              parts: formData.parts,
              descriptionDone: formData.descriptionDone,
              actTimeMin: formData.actTimeMin,
              status: "completed",
              fixDate: new Date().toISOString().substring(0, 10),
            };
            console.log(editedJob.parts);
            await axios({
              method: "PATCH",
              url: `http://localhost:8080/jobs/${props.job.idJob}`,
              data: editedJob,
              headers: { Authorization: `Bearer ${authCtx.authData.token}` },
            });
            editedJob.parts.forEach(async (p) => {
              try {
                await axios({
                  method: "PATCH",
                  url: `http://localhost:8080/jobs/${props.job.idJob}/${p.idPart}/${p.quantityUsed}`,
                  headers: {
                    Authorization: `Bearer ${authCtx.authData.token}`,
                  },
                });
              } catch (err) {
                console.log(err);
              }
            });
          } catch (err) {
            console.log(err);
          } finally {
            props.obtainJobs();
            setShow(false);
          }
        };
        setForm(
          <CompleteJob
            formData={formData}
            descriptionRequired={props.job.descriptionRequired}
            estTimeMin={props.job.estTimeMin}
          />
        );
        break;
      }
      case 3: {
        formData = { descriptionRequired: props.job.descriptionRequired };
        submitAction.current = async () => {
          const editedJob = {
            ...props.job,
            descriptionRequired: formData.descriptionRequired,
          };
          try {
            const response = await axios({
              method: "PATCH",
              url: `http://localhost:8080/jobs/${props.job.idJob}`,
              data: editedJob,
              headers: { Authorization: `Bearer ${authCtx.authData.token}` },
            });
            console.log(response);
          } catch (err) {
            console.log(err);
          } finally {
            props.obtainJobs();
            setShow(false);
          }
        };

        setForm(<EditDescriptionRequired formData={formData} />);
        break;
      }

      default: {
        formData = {};
        submitAction.current = async () => {
          try {
            const response = await axios({});
            console.log(response);
          } catch (err) {
            console.log(err);
          } finally {
            props.obtainJobs();
            setShow(false);
          }
        };

        setForm(<UserAssignment />);
        break;
      }
    }
    handleShow();
  };
  return (
    <>
      <tr>
        <td>{props.job.idJob}</td>
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
              {props.jobType === "active" &&
              (authCtx.authData.role === "ROLE_FOREPERSON" ||
                authCtx.authData.role === "ROLE_MECHANIC"|| authCtx.authData.role === "ROLE_FRANCHISEE") ? (
                <>
                  <Dropdown.Item onClick={() => formHandler(2)}>
                    Set completed
                  </Dropdown.Item>

                  <Dropdown.Item onClick={() => formHandler(1)}>
                    Change assignment
                  </Dropdown.Item>
                </>
              ) : null}
              {props.jobType === "booked" ? (
                <>
                  {authCtx.authData.role === "ROLE_FOREPERSON" ||
                  authCtx.authData.role === "ROLE_MECHANIC" || authCtx.authData.role === "ROLE_FRANCHISEE" ? (
                    <Dropdown.Item onClick={() => formHandler(1)}>
                      Assign mechanic
                    </Dropdown.Item>
                  ) : null}
                  <Dropdown.Item onClick={() => formHandler(3)}>
                    Change description
                  </Dropdown.Item>
                </>
              ) : null}
              {authCtx.authData.role === "ROLE_FOREPERSON" || authCtx.authData.role === "ROLE_FRANCHISEE" ? (
                <Dropdown.Item
                  style={{ backgroundColor: "rgba(242, 97, 99,0.2)" }}
                  onClick={() => props.deleteJob(props.job.idJob)}
                >
                  Delete
                </Dropdown.Item>
              ) : null}
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
