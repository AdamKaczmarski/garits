import { useState, useEffect, useCallback } from "react";
import axios from "axios";
import Spinner from "react-bootstrap/Spinner";
import ListGroup from "react-bootstrap/ListGroup";
import Accordion from "react-bootstrap/Accordion";
import BoldSpan from "../CommonComponents/BoldSpan";
const JobDetails = (props) => {
  const [isLoading, setIsLoading] = useState(true);
  const [job, setJob] = useState({});
  const obtainJobDetails = useCallback(async () => {
    try {
      const response = await axios({
        method: "GET",
        url: `http://localhost:8080/jobs/${props.idJob}`,
      });
      console.log(response);
      setJob(response.data);
    } catch (err) {
      console.log(err);
    } finally {
      setIsLoading(false);
    }
  }, [props]);

  useEffect(() => {
    obtainJobDetails();
  }, [obtainJobDetails]);
  if (isLoading) return <Spinner animation="border" variant="primary" />;
  let bay = null;
  if (job.bay && job.bay !== "MOT") {
    bay = job.bay.charAt(0).toUpperCase() + job.bay.slice(1).toLowerCase();
  } else bay = job.bay;
  let bookingDate;
  if (job.bookingDate)
    bookingDate = new Date(job.bookingDate).toISOString().substring(0, 10);
  let fixDate;
  if (job.fixDate)
    fixDate = new Date(job.fixDate).toISOString().substring(0, 10);
  let createdDate;
  if (job.createDate)
    createdDate = new Date(job.createDate).toISOString().substring(0, 10);
  let services;
  if (job.services && job.services.length > 0) {
    services = job.services.map((service) => (
      <ListGroup.Item key={service.idService}>
        <span>{service.serviceName}</span>
      </ListGroup.Item>
    ));
  }
  let parts;
  if (job.parts && job.parts.length > 0) {
    parts = job.parts.map((part) => (
      <ListGroup.Item key={part.idPart}>
        <span>{part.quantityUsed + " x " + part.partName}</span>
      </ListGroup.Item>
    ));
  }

  return (
    <>
      {" "}
      <ListGroup>
        <ListGroup.Item>
          <BoldSpan>Job ID</BoldSpan>: {job.idJob}
        </ListGroup.Item>
        <ListGroup.Item>
          <BoldSpan>Customer</BoldSpan>: {job.vehicle.customer[0].name}
        </ListGroup.Item>
        <ListGroup.Item>
          <BoldSpan>Registration Number</BoldSpan>: {job.vehicle.idRegNo}
        </ListGroup.Item>
        <ListGroup.Item>
          <BoldSpan>Car</BoldSpan>:{" "}
          {job.vehicle.manufacturer + " " + job.vehicle.model}
        </ListGroup.Item>
        <ListGroup.Item>
          <BoldSpan>Created date</BoldSpan>: {createdDate}
        </ListGroup.Item>
        <ListGroup.Item>
          <BoldSpan>Booking date</BoldSpan>: {bookingDate}
        </ListGroup.Item>
        {job.status === "completed" || job.status === "active" ? (
          <ListGroup.Item>
            <BoldSpan>Assigned mechaninc</BoldSpan>:{" "}
            {job.user[0].firstName + " " + job.user[0].lastName}
          </ListGroup.Item>
        ) : null}
        {job.status === "completed" ? (
          <>
            {" "}
            <ListGroup.Item>
              <BoldSpan>Fix date</BoldSpan>: {fixDate}
            </ListGroup.Item>
          </>
        ) : null}
        <ListGroup.Item>
          <BoldSpan>Bay</BoldSpan>: {bay}
        </ListGroup.Item>
        <ListGroup.Item>
          <BoldSpan>Description Required</BoldSpan>: {job.descriptionRequired}
        </ListGroup.Item>
        <ListGroup.Item>
          <BoldSpan>Estimated time</BoldSpan>: {job.estTimeMin + " mins"}
        </ListGroup.Item>
        {job.status === "completed" ? (
          <>
            <ListGroup.Item>
              <BoldSpan>Description Done</BoldSpan>: {job.descriptionDone}
            </ListGroup.Item>
            <ListGroup.Item>
              <BoldSpan>Time taken</BoldSpan>: {job.actTimeMin + " mins"}
            </ListGroup.Item>
          </>
        ) : null}
        <ListGroup.Item>
          <Accordion>
            <Accordion.Item eventKey={0}>
              <Accordion.Header>Services included</Accordion.Header>
              <Accordion.Body>{services}</Accordion.Body>
            </Accordion.Item>
            {job.status === "completed" ? (
              <>
                <Accordion.Item eventKey={1}>
                  <Accordion.Header>Parts used</Accordion.Header>
                  <Accordion.Body>{parts}</Accordion.Body>
                </Accordion.Item>
              </>
            ) : null}
          </Accordion>
        </ListGroup.Item>
      </ListGroup>
    </>
  );
};

export default JobDetails;
