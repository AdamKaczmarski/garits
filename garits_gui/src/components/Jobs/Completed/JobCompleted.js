import Dropdown from "react-bootstrap/Dropdown";
import { useState, useContext } from "react";
import Button from "react-bootstrap/Button";
import CustomModal from "../../CommonComponents/CustomModal";
import JobDetails from "../JobDetails";
import AuthContext from "../../../store/auth-context";
import axios from "axios";
const JobCompleted = (props) => {
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);
  const [showDetails, setShowDetails] = useState(false);
  const handleShowDetails = () => setShowDetails(!showDetails);
  const authCtx = useContext(AuthContext);
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
  const downloadJobSheet = async () => {
    try {
      const response = await axios({
        method: "GET",
        url: "http://localhost:8080/pdf/job-sheet/" + props.job.idJob,
        headers: {Authorization: `Bearer ${authCtx.authData.token}`},
        responseType: "blob",
      });
      if (response.status === 200) {
        const url = window.URL.createObjectURL(new Blob([response.data]));
        const link = document.createElement("a");
        link.href = url;
        link.setAttribute("download", "Job_sheet.pdf");
        document.body.appendChild(link);
        link.click();
      }
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <>
      <tr>
        <td>{props.job.idJob}</td>
        <td>
          {props.job.vehicle.customer[0].name
            ? props.job.vehicle.customer[0].name
            : null}
        </td>
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
              <Dropdown.Item onClick={downloadJobSheet}>
                Download report
              </Dropdown.Item>
              {authCtx.authData.role === "ROLE_FOREPERSON" ? (
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
    </>
  );
};

export default JobCompleted;
