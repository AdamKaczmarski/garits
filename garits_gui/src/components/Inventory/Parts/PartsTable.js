import { useState } from "react";
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";
import InventoryModal from "../InventoryModal";

import { INVENTORY } from "../../../dummy-data/inventory";
import Part from "./Part";
import AddToInventoryForm from "../AddToInventoryForm";

const PartsTable = () => {
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);
  const parts = INVENTORY.map((part) => <Part key={part.id} part={part} />);
  return (
    <>
      <Table striped hover className="mt-3">
        <thead>
          <tr>
            <th>Code</th>
            <th>Name</th>
            <th>Type</th>
            <th>Manufacturer</th>
            <th>Vehicle type</th>
            <th>Year(s)</th>
            <th>Price</th>
            <th>Stock level</th>
            <th
            /* style={{ display: "flex" }}
              className="justify-content-between  " */
            >
              <span className="pr-3">Actions</span>
            </th>
            <th>
              <Button variant="outline-primary" onClick={handleShow}>
                +
              </Button>
            </th>
          </tr>
        </thead>
        <tbody>{parts}</tbody>
      </Table>
      <InventoryModal
        show={show}
        onClose={handleShow}
        title="Add part"
        form={<AddToInventoryForm />}
      />
    </>
  );
};

export default PartsTable;
