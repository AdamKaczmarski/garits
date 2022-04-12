import Tabs from "react-bootstrap/Tabs";
import Tab from "react-bootstrap/Tab";
import { useContext } from "react";
import PartsTable from "./Parts/PartsTable";
import OrdersTable from "./Orders/OrdersTable";
import AuthContext from "../../store/auth-context";

const InventoryTabs = () => {
  const authCtx = useContext(AuthContext);
  if (
    authCtx.authData.role === "ROLE_FOREPERSON" ||
    authCtx.authData.role === "ROLE_RECEPTIONIST"|| authCtx.authData.role === "ROLE_FRANCHISEE"
  ) {
    return (
      <Tabs defaultActiveKey="inventory" fill mountOnEnter={true}>
        <Tab eventKey="inventory" title="Inventory">
          <PartsTable />
        </Tab>
        <Tab eventKey="orders" title="Orders">
          <OrdersTable />
        </Tab>
      </Tabs>
    );
  } else if (authCtx.authData.role === "ROLE_MECHANIC") {
    return (
      <Tabs defaultActiveKey="inventory" fill mountOnEnter={true}>
        <Tab eventKey="inventory" title="Inventory">
          <PartsTable />
        </Tab>
      </Tabs>
    );
  } else {
    return <p>Not authenticated</p>;
  }
};

export default InventoryTabs;
