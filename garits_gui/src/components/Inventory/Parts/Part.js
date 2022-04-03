import Dropdown from 'react-bootstrap/Dropdown';
import { useState } from 'react';
import InventoryModal from '../InventoryModal';
import EditPartForm from './EditPartForm';
const Part = props => {
    const [show, setShow] = useState(false);
    const handleShow = () => setShow(!show);
  
    return(<>
        <tr>
            <td>{props.part.code}</td>
            <td>{props.part.part_name}</td>
            <td>{props.part.part_type}</td>
            <td>{props.part.manufacturer}</td>
            <td>{props.part.vehicle_type}</td>
            <td>{props.part.year_s}</td>
            <td>{(Math.round(props.part.price * 100) / 100).toFixed(2) + " GBP"}</td>
            <td>{props.part.stock_level}</td>
            <td colSpan={2}>
        <Dropdown>
          <Dropdown.Toggle variant="secondary">Action</Dropdown.Toggle>

          <Dropdown.Menu>
            <Dropdown.Item onClick={handleShow}>Edit</Dropdown.Item>
            <Dropdown.Item
              style={{ backgroundColor: "rgba(242, 97, 99,0.2)" }}
              href="#/action-3"
            >
              Delete
            </Dropdown.Item>
          </Dropdown.Menu>
        </Dropdown>{" "}
      </td>
      <td></td>
        </tr><InventoryModal show={show} onClose={handleShow} title={"Edit "+props.part.part_name} form={<EditPartForm part={props.part}/>} /></>
    )
}

export default Part;