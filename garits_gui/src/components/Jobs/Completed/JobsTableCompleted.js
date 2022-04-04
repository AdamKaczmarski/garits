import { useEffect, useState } from "react";
import axios from "axios";
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";
import Spinner from "react-bootstrap/Spinner";
import JobCompleted from "./JobCompleted";
import AddJob from "../AddJob";
import CustomModal from "../../CommonComponents/CustomModal";

const JobsTableCompleted = (props) => {
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);
  const [isLoading, setIsLoading] = useState(true);
  const [jobs, setJobs] = useState([]);
  const obtainCompletedJobs = async () => {
    try {
      const response = await axios({
        method: "GET",
        url: "http://localhost:8080/jobs-completed",
      });
      console.log(response);
      if (response.status === 200) setJobs(response.data);
    } catch (err) {
      console.log(err);
    } finally {
      setIsLoading(false);
    }
  };
  useEffect(() => {
    obtainCompletedJobs();
  }, []);
  if (isLoading) {
    return <Spinner variant="primary" animation="border" />;
  }
  let jobsView = jobs.map((job) => <JobCompleted job={job} key={job.idJob} />);
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
        submitAction={()=>{}}
        form={<AddJob />}
      />
    </>
  );
};

export default JobsTableCompleted;
