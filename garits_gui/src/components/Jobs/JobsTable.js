import { useState } from "react";
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";

import { JOBS } from "../../dummy-data/jobs";
import Job from "./Job";
import AddJob from "./AddJob";
import JobModal from "./JobModal";

const JobsTable = (props) => {
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);
  const jobs = JOBS.filter((job) => props.jobType === job.status).map((job) => (
    <Job job={job} jobType={props.jobType} />
  ));
  return (
    <>
      <Table striped hover className="mt-3">
        <thead>
          <tr>
            <th>Customer</th>
            <th>Car Reg. No.</th>
            <th>Car</th>
            {props.jobType === "active" || props.jobType === "completed" ? (
              <th>Bay</th>
            ) : null}
            <th>Desc. Required Services</th>
            <th>Estimated time</th>
            {props.jobType === "active" || props.jobType === "completed" ? (
              <th>Assignee</th>
            ) : null}
            {props.jobType === "active" || props.jobType === "completed" ? (
              <th>Desc. Done</th>
            ) : null}
            {props.jobType === "active" || props.jobType === "completed" ? (
              <th>Actual time</th>
            ) : null}
            {props.jobType === "booked" ? <th>Created date</th> : null}
            <th>Booking Date</th>
            {props.jobType === "completed" ? <th>Fix date</th> : null}
            <th>Action</th>
            <th>
              <Button variant="outline-primary" onClick={handleShow}>
                +
              </Button>
            </th>
          </tr>
        </thead>
        <tbody>{jobs}</tbody>
      </Table>
      <JobModal title="Add job" show={show} onClose={handleShow} form={<AddJob />} />
    </>
  );
};

export default JobsTable;
