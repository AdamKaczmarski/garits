import { useState, useEffect } from "react";
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";
import axios from "axios";
import Spinner from "react-bootstrap/Spinner";
import Job from "../Job";
import AddJob from "../AddJob";
import CustomModal from "../../CommonComponents/CustomModal";

const JobsTableBooked = () => {
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);
  const [isLoading, setIsLoading] = useState(true);
  const [jobs, setJobs] = useState([]);
  let selectedServices = [];
  let newJob = {
    vehicle: { idVehicle: 0 },
    bookingDate: null,
    status: "booked",
    services: selectedServices,
  };
  const addJob = async () => {
    try {
      const response = await axios({
        method: "POST",
        url: "http://localhost:8080/jobs",
        data: newJob,
      });
      console.log(response);
    } catch (err) {
      console.log(err);
    } finally {
      handleShow();
      obtainBookedJobs();
    }
  };

  const obtainBookedJobs = async () => {
    try {
      const response = await axios({
        method: "GET",
        url: "http://localhost:8080/jobs-booked",
      });
      console.log(response);
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
      });
      console.log(response);
    } catch (err) {
      console.log(err);
    } finally {
      handleShow();
      obtainBookedJobs();
    }
  };

  useEffect(() => {
    obtainBookedJobs();
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
      obtainJobs={obtainBookedJobs}
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
            <th>Desc. Required Services</th>
            <th>Estimated time</th>
            <th>Created date</th>
            <th>Booking Date</th>
            <th>Action</th>
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

export default JobsTableBooked;
