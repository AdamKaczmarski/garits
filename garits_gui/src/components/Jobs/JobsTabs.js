import Tabs from "react-bootstrap/Tabs";
import Tab from "react-bootstrap/Tab";
import JobsTableBooked from "./Booked/JobsTableBooked";
import JobsTableCompleted from "./Completed/JobsTableCompleted";
import JobsTableActive from "./Active/JobsTableActive";

const JobsTabs = () => {
  return (
    <Tabs defaultActiveKey="active" fill mountOnEnter={true}>
      <Tab eventKey="booked" title="Booked">
        <JobsTableBooked jobType="booked"/>
      </Tab>
      <Tab eventKey="active" title="In Progress">
        <JobsTableActive jobType="active"/>
      </Tab>
      <Tab eventKey="completed" title="Completed">
        <JobsTableCompleted jobType="completed"/>
      </Tab>
    </Tabs>
  );
};

export default JobsTabs;
