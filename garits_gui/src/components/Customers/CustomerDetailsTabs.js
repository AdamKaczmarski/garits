import Tabs from "react-bootstrap/Tabs";
import Tab from "react-bootstrap/Tab";
import CustomerDetailsContent from "./CustomerDetailsContent";
import CustomerDetailsVehicles from "./CustomerDetailsVehicles";

const CustomerDetailsTabs = (props) => {
  return (
    <Tabs defaultActiveKey="info">
      <Tab eventKey="info" title="Info">
        <CustomerDetailsContent customer={props.customer} />
      </Tab>
      <Tab eventKey="vehicles" title="Vehicles">
        <CustomerDetailsVehicles customer_id={props.customer.id} />
      </Tab>
    </Tabs>
  );
};

export default CustomerDetailsTabs;
