import Tabs from "react-bootstrap/Tabs";
import Tab from "react-bootstrap/Tab";

import PartsTable from "./Parts/PartsTable";
import OrdersTable from "./Orders/OrdersTable";

const InventoryTabs = () => {
  return (
    <Tabs defaultActiveKey="inventory" fill>
      <Tab eventKey="inventory" title="Inventory">
        <PartsTable />
      </Tab>
      <Tab eventKey="orders" title="Orders">
        <OrdersTable />
      </Tab>
    </Tabs>
  );
};

export default InventoryTabs;
