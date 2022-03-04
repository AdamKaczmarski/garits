import Tabs from "react-bootstrap/Tabs";
import Tab from "react-bootstrap/Tab";
import CustomerDetailsContent from "./CustomerDetailsContent";
import CustomerDetailsVehicles from "./CustomerDetailsVehicles";
import ReportsTable from './CustomerReports/ReportsTable';
const CustomerDetailsTabs = (props) => {
  return (
    <Tabs defaultActiveKey="info">
      <Tab eventKey="info" title="Info">
        <CustomerDetailsContent customer={props.customer} />
      </Tab>
      <Tab eventKey="vehicles" title="Vehicles">
        <CustomerDetailsVehicles customer_id={props.customer.id} />
      </Tab>
      <Tab eventKey="reports" title="Reports">
        <ReportsTable customer_id={props.customer.id} />
      </Tab>
    </Tabs>
  );
};

export default CustomerDetailsTabs;
