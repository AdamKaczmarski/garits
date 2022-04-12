import { useState, useEffect, useContext } from "react";
import axios from "axios";
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";
import InventoryModal from "../InventoryModal";
import Part from "./Part";
import AddToInventoryForm from "../AddToInventoryForm";
import  Spinner  from "react-bootstrap/Spinner";
import AuthContext from '../../../store/auth-context';
const PartsTable = () => {
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);
  const [isLoading, setIsLoading] = useState(true);
  const [parts, setParts] = useState([]);
  const authCtx = useContext(AuthContext);
  let newPart = {
    partName: "",
    partType: "",
    code: "",
    manufacturer: "",
    vehicleType: "",
    yearS: "",
    price: 0,
    stockLevel: 0,
    stockeLevelThreshold: 0,
  };

    const deletePart = async (idPart) => {
    try {
      const response = await axios({
        method: "DELETE",
        url: "http://localhost:8080/parts/" + idPart,
        headers: { Authorization: `Bearer ${authCtx.authData.token}` },
      });
      console.log(response);
    } catch (err) {
      console.log(err);
    } finally {
      const newParts = parts.filter((p) => p.idPart !== idPart);
      setParts(newParts);
    }
  };

  const obtainParts = async () => {
    try {
      const response = await axios({
        method: "GET",
        url: "http://localhost:8080/parts",
        headers: { Authorization: `Bearer ${authCtx.authData.token}` },
      });
      setParts(response.data);
    } catch (err) {
      console.log(err);
    } finally {
      setIsLoading(false);
    }
  };
  const addPart = async () => {
    try {
      const response = await axios({
        method: "POST",
        url: "http://localhost:8080/parts/",
        data: newPart,
        headers: { Authorization: `Bearer ${authCtx.authData.token}` },
      });
      console.log(response);
    } catch (err) {
      console.log(err);
    } finally {
      obtainParts();
      handleShow();
    }
  };
  useEffect(() => {
    obtainParts();
  }, []);
  if (isLoading) {
    return <Spinner variant="primary" />;
  }
  const partsView = parts.map((part) => (
    <Part
      key={part.idPart}
      part={part}
      deletePart={deletePart}
      obtainParts={obtainParts}
    />
  ));

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
        <tbody>{partsView}</tbody>
      </Table>
      <InventoryModal
        show={show}
        onClose={handleShow}
        title="Add part"
        submitAction={addPart}
        form={<AddToInventoryForm newPart={newPart} />}
      />
    </>
  );
};

export default PartsTable;
