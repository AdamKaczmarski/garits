import { useState, useEffect } from "react";
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";
import axios from "axios";
import Job from "../Job";
import Spinner from "react-bootstrap/Spinner";
import AddJob from "../AddJob";
import CustomModal from "../../CommonComponents/CustomModal";
const JobsTableActive = () => {
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);
  const [isLoading, setIsLoading] = useState(true);
  const [jobs, setJobs] = useState([]);
  const obtainActiveJobs = async () => {
    try {
      const response = await axios({
        method: "GET",
        url: "http://localhost:8080/jobs-active",
      });
      console.log(response);
      if (response.status === 200) setJobs(response.data);
    } catch (err) {
      console.log(err);
    } finally {
      setIsLoading(false);
    }
  };
  const deleteJob = async (idJob)=>{
    try {
      const response = await axios({
        method:"DELETE",
        url:`http://localhost:8080/jobs/${idJob}`
      });
      console.log(response);
    } catch (err) {
      console.log(err);
    } finally {
      obtainActiveJobs();
    }
  }
  useEffect(() => {
    obtainActiveJobs();
  }, []);
  if (isLoading) {
    return <Spinner variant="primary" animation="border" />;
  }
  let jobsView = jobs.map((job) => <Job job={job} key={job.idJob} jobType={job.status} deleteJob={deleteJob}/>);
  return (
    <>
      <Table striped hover className="mt-3">
        <thead>
          <tr>
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
        submitAction={()=>{}}
        form={<AddJob />}
      />
    </>
  );
};

export default JobsTableActive;
