import { useEffect, useState, useContext } from "react";
import axios from "axios";
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";
import Spinner from "react-bootstrap/Spinner";
import JobCompleted from "./JobCompleted";
import AddJob from "../AddJob";
import CustomModal from "../../CommonComponents/CustomModal";
import AuthContext from "../../../store/auth-context";
const JobsTableCompleted = (props) => {
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
  };
  const addJob = async () => {
    console.log(newJob);
    try {
      const response = await axios({
        method: "POST",
        url: "http://localhost:8080/jobs",
        headers:{'Authorization': `Bearer ${authCtx.authData.token}`},

        data: newJob,
      });
      console.log(response.data);
    } catch (err) {
      console.log(err);
    }
  };

  const obtainCompletedJobs = async () => {
    try {
      const response = await axios({
        method: "GET",
        url: "http://localhost:8080/jobs-completed",
        headers:{'Authorization': `Bearer ${authCtx.authData.token}`}

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
        headers:{'Authorization': `Bearer ${authCtx.authData.token}`}
      });
      console.log(response);
    } catch (err) {
      console.log(err);
    } finally {
      obtainCompletedJobs();
    }
  };

  useEffect(() => {
    obtainCompletedJobs();
  }, []);
  if (isLoading) {
    return <Spinner variant="primary" animation="border" />;
  }
  let jobsView = jobs.map((job) => (
    <JobCompleted
      job={job}
      key={job.idJob}
      jobType={job.status}
      deleteJob={deleteJob}
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
            <th>Desc. Done Services</th>
            <th>Estimated time</th>
            <th>Assignee</th>
            <th>Actual time</th>
            <th>Booking Date</th>
            <th>Fix date</th>
            <th>Actions</th>
            <th>
              <Button variant="outline-primary" onClick={handleShow}>
                +
              </Button>
            </th>
          </tr>
        </thead>
        <tbody>{jobsView}</tbody>
      </Table>
      <CustomModal
        title="Add job"
        show={show}
        onClose={handleShow}
        submitAction={addJob}
        form={<AddJob selectedServices={selectedServices} newJob={newJob} />}
      />
    </>
  );
};

export default JobsTableCompleted;
