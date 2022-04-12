import { useContext } from "react";
import Tabs from "react-bootstrap/Tabs";
import Tab from "react-bootstrap/Tab";
import PaymentsJobsTable from "./Job/PaymentsJobsTable";
import PaymentsRetailTable from "./Retail/PaymentsRetailTable";
import AuthContext from "../../store/auth-context";

const PaymentsTabs = () => {
  const authCtx = useContext(AuthContext);
  if (authCtx.authData.role === "ROLE_RECEPTIONIST") {
    return (
      <Tabs defaultActiveKey="jobs" fill mountOnEnter={true}>
        <Tab eventKey="jobs" title="Jobs">
          <PaymentsJobsTable />
        </Tab>
        <Tab eventKey="retail" title="Retail">
          <PaymentsRetailTable />
        </Tab>
      </Tabs>
    );
  }
  return <p>Not authenticated</p>;
};

export default PaymentsTabs;
