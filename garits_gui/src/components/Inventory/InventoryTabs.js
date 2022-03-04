import Tabs from "react-bootstrap/Tabs";
import Tab from "react-bootstrap/Tab";

import InventoryTable from "./InventoryTable";

const InventoryTabs = () => {
  return (
    <Tabs defaultActiveKey="inventory" fill>
      <Tab eventKey="inventory" title="Inventory">
        <InventoryTable />
      </Tab>
      <Tab eventKey="orders" title="Orders">
        <InventoryTable />
      </Tab>
    </Tabs>
  );
};

export default InventoryTabs;
