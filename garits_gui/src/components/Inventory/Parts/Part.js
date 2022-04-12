import Dropdown from "react-bootstrap/Dropdown";
import { useState, useContext } from "react";
import axios from "axios";
import InventoryModal from "../InventoryModal";
import EditPartForm from "./EditPartForm";
import AuthContext from "../../../store/auth-context";

import BoldSpan from "../../CommonComponents/BoldSpan";
const Part = (props) => {
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);
  const authCtx = useContext(AuthContext);

  let editedPart = { ...props.part };

  const editPart = async () => {
    try {
      const response = await axios({
        method: "PATCH",
        url: "http://localhost:8080/parts/" + editedPart.idPart,
        data: editedPart,
        headers: { Authorization: `Bearer ${authCtx.authData.token}` },
      });
      console.log(response);
    } catch (err) {
      console.log(err);
    } finally {
      handleShow();
      props.obtainParts();
    }
  };

  return (
    <>
      <tr
        style={
          props.part.stockLevel < props.part.stockLevelThreshold
            ? { backgroundColor: "rgba(242, 97, 99,0.2)" }
            : null
        }
      >
        <td>{props.part.code}</td>
        <td>{props.part.partName}</td>
        <td>{props.part.partType}</td>
        <td>{props.part.manufacturer}</td>
        <td>{props.part.vehicleType}</td>
        <td>{props.part.yearS}</td>
        <td>
          {(Math.round(props.part.price * 100) / 100).toFixed(2) + " GBP"}
        </td>
        <td>
          {props.part.stockLevel < props.part.stockLevelThreshold ? (
            <BoldSpan>{props.part.stockLevel}</BoldSpan>
          ) : (
            props.part.stockLevel
          )}
        </td>
        <td colSpan={2}>
          <Dropdown>
            <Dropdown.Toggle variant="secondary">Action</Dropdown.Toggle>

            <Dropdown.Menu>
              <Dropdown.Item onClick={handleShow}>Edit</Dropdown.Item>
              <Dropdown.Item
                style={{ backgroundColor: "rgba(242, 97, 99,0.2)" }}
                onClick={() => props.deletePart(props.part.idPart)}
              >
                Delete
              </Dropdown.Item>
            </Dropdown.Menu>
          </Dropdown>{" "}
        </td>
        <td></td>
      </tr>
      <InventoryModal
        show={show}
        onClose={handleShow}
        title={"Edit " + props.part.partName}
        submitAction={editPart}
        form={<EditPartForm part={props.part} editedPart={editedPart} />}
      />
    </>
  );
};

export default Part;
