import Tabs from "react-bootstrap/Tabs";
import Tab from "react-bootstrap/Tab";
import CustomerDetailsContent from "./CustomerDetailsContent";
import CustomerDetailsVehicles from "./CustomerDetailsVehicles";
import ReportsTable from "./CustomerReports/ReportsTable";
const CustomerDetailsTabs = (props) => {
  return (
    <Tabs defaultActiveKey="info">
      <Tab eventKey="info" title="Info" /* onClick={props.setIsReportsTab(false)} */>
        <CustomerDetailsContent customer={props.customer} />
      </Tab>
      <Tab
        eventKey="vehicles"
        title="Vehicles"
/*         onClick={props.setIsReportsTab(false)}
 */      >
        <CustomerDetailsVehicles customer_id={props.customer.id} />
      </Tab>
      <Tab
        eventKey="reports"
        title="Reports"
/*         onClick={props.setIsReportsTab(true)}
 */      >
        <ReportsTable customer_id={props.customer.id} />
      </Tab>
    </Tabs>
  );
};

export default CustomerDetailsTabs;
