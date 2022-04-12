import { useContext } from "react";
import AuthContext from "../../store/auth-context";
import Tabs from "react-bootstrap/Tabs";
import Tab from "react-bootstrap/Tab";
import JobsTableBooked from "./Booked/JobsTableBooked";
import JobsTableCompleted from "./Completed/JobsTableCompleted";
import JobsTableActive from "./Active/JobsTableActive";

const JobsTabs = () => {
  const authCtx = useContext(AuthContext);
  if (
    authCtx.authData.role === "ROLE_FRANCHISEE" ||
    authCtx.authData.role === "ROLE_FOREPERSON" ||
    authCtx.authData.role === "ROLE_RECEPTIONIST"
  ) {
    return (
      <Tabs defaultActiveKey="active" fill mountOnEnter={true}>
        <Tab eventKey="booked" title="Booked">
          <JobsTableBooked jobType="booked" />
        </Tab>
        <Tab eventKey="active" title="In Progress">
          <JobsTableActive jobType="active" />
        </Tab>
        <Tab eventKey="completed" title="Completed">
          <JobsTableCompleted jobType="completed" />
        </Tab>
      </Tabs>
    );
  } else if (authCtx.authData.role === "ROLE_MECHANIC") {
    return (
      <Tabs defaultActiveKey="active" fill mountOnEnter={true}>
        <Tab eventKey="booked" title="Booked">
          <JobsTableBooked jobType="booked" />
        </Tab>
        <Tab eventKey="active" title="In Progress">
          <JobsTableActive jobType="active" />
        </Tab>
      </Tabs>
    );
  } else {
    return <p>Not authorized</p>;
  }
};

export default JobsTabs;
