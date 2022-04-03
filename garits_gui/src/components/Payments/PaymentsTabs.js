import Tabs from "react-bootstrap/Tabs";
import Tab from "react-bootstrap/Tab";
import PaymentsJobsTable from "./PaymentsJobsTable";
import PaymentsRetailTable from "./Retail/PaymentsRetailTable";

const PaymentsTabs = () => {
  return (
    <Tabs defaultActiveKey="jobs" fill>
      <Tab eventKey="jobs" title="Jobs">
        <PaymentsJobsTable />
      </Tab>
      <Tab eventKey="retail" title="Retail">
        <PaymentsRetailTable />
      </Tab>
    </Tabs>
  );
};

export default PaymentsTabs;
