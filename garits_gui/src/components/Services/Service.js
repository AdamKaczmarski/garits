import Dropdown from "react-bootstrap/Dropdown";
import { useState } from "react";
import EditServiceForm from "./EditServiceForm";
import ServiceModal from "./ServiceModal";

const Service = (props) => {
  /* Implement this for short_decsription and then only add edit modal
    https://react-bootstrap.github.io/components/overlays/#disabled-elements */
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);

  return (
    <>
      <tr>
        <td>{props.service.service_name}</td>
        {/* <td>{(Math.round(props.service.service_price * 100) / 100).toFixed(2)+' GBP'}</td> */}
        <td>{(Math.round(props.service.service_price * 100) / 100).toFixed(2) + " GBP"}</td>
        <td>{props.service.approx_time_min + " min"}</td>
        <td>
          {props.service.short_description.length > 70
            ? props.service.short_description.substring(0, 67) + "..."
            : props.service.short_description}
        </td>
        <td>
          <Dropdown className="m-0 p-0" align={"end"}>
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
          </Dropdown>
        </td>
      </tr>
      <ServiceModal
        show={show}
        onClose={handleShow}
        title={"Edit service - "+props.service.service_name}
        form={<EditServiceForm service={props.service}/>}
      />
    </>
  );
};

export default Service;
