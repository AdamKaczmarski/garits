import Tabs from "react-bootstrap/Tabs";
import Tab from "react-bootstrap/Tab";
import JobsTable from "./JobsTable";

const JobsTabs = () => {
  return (
    <Tabs defaultActiveKey="active" fill>
      <Tab eventKey="booked" title="Booked">
        <JobsTable jobType="booked"/>
      </Tab>
      <Tab eventKey="active" title="Active">
        <JobsTable jobType="active"/>
      </Tab>
      <Tab eventKey="completed" title="Completed">
        <JobsTable jobType="completed"/>
      </Tab>
    </Tabs>
  );
};

export default JobsTabs;
