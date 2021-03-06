import { useState, useEffect, useContext } from "react";
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";
import axios from "axios";
import Job from "../Job";
import Spinner from "react-bootstrap/Spinner";
import AddJob from "../AddJob";
import CustomModal from "../../CommonComponents/CustomModal";
import AuthContext from "../../../store/auth-context";
const JobsTableActive = () => {
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);
  const [isLoading, setIsLoading] = useState(true);
  const [jobs, setJobs] = useState([]);
  const authCtx = useContext(AuthContext);
  let selectedServices = [];
  let newJob = {
    vehicle: { idVehicle: 0 },
    bookingDate: null,
    status: "booked",
    services: selectedServices,
    bay: "",
  };
  const addJob = async () => {
    console.log(newJob);
    try {
      const response = await axios({
        method: "POST",
        url: "http://localhost:8080/jobs",
        data: newJob,
        headers: { Authorization: `Bearer ${authCtx.authData.token}` },
      });
      handleShow();
    } catch (err) {
      console.log(err);
    } finally {
      obtainActiveJobs();
    }
  };
  const obtainActiveJobs = async () => {
    try {
      const response = await axios({
        method: "GET",
        url: "http://localhost:8080/jobs-active",
        headers: { Authorization: `Bearer ${authCtx.authData.token}` },
      });
      console.log(response.data);
      if (response.status === 200) setJobs(response.data);
    } catch (err) {
      console.log(err);
    } finally {
      setIsLoading(false);
    }
  };
  const deleteJob = async (idJob) => {
    try {
      const response = await axios({
        method: "DELETE",
        url: `http://localhost:8080/jobs/${idJob}`,
        headers: { Authorization: `Bearer ${authCtx.authData.token}` },
      });
      console.log(response);
    } catch (err) {
      console.log(err);
    } finally {
      obtainActiveJobs();
    }
  };
  useEffect(() => {
    obtainActiveJobs();
  }, []);
  if (isLoading) {
    return <Spinner variant="primary" animation="border" />;
  }
  let jobsView = jobs.map((job) => (
    <Job
      job={job}
      key={job.idJob}
      jobType={job.status}
      deleteJob={deleteJob}
      addjob={addJob}
      obtainJobs={obtainActiveJobs}
    />
  ));
  return (
    <>
      <Table striped hover className="mt-3">
        <thead>
          <tr>
            <th>Job ID</th>

            <th>Customer</th>
            <th>Car Reg. No.</th>
            <th>Car</th>
            <th>Bay</th>
            <th>Desc. Required Services</th>
            <th>Estimated time</th>
            <th>Assignee</th>
            <th>Booking Date</th>
            <th>Action</th>
            <th>
              {authCtx.authData.role !== "ROLE_MECHANIC" ? (
                <Button variant="outline-primary" onClick={handleShow}>
                  +
                </Button>
              ) : null}
            </th>
          </tr>
        </thead>
        <tbody>{jobsView}</tbody>
      </Table>
      {authCtx.authData.role !== "ROLE_MECHANIC" ? (
        <CustomModal
          title="Add job"
          show={show}
          onClose={handleShow}
          submitAction={addJob}
          form={<AddJob selectedServices={selectedServices} newJob={newJob} />}
        />
      ) : null}
    </>
  );
};

export default JobsTableActive;
