import Dropdown from "react-bootstrap/Dropdown";
import { useState, useContext } from "react";
import EditServiceForm from "./EditServiceForm";
import ServiceModal from "./ServiceModal";
import axios from "axios";
import AuthContext from "../../store/auth-context";
const Service = (props) => {
  /* Implement this for short_decsription and then only add edit modal
    https://react-bootstrap.github.io/components/overlays/#disabled-elements */
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);
  const editedService = { ...props.service };
  const authCtx = useContext(AuthContext);
  const deleteService = async () => {
    try {
      const response = await axios({
        method: "DELETE",
        url: "http://localhost:8080/services/" + props.service.idService,
        headers:{'Authorization': `Bearer ${authCtx.authData.token}`}

      });
      console.log(response);
    } catch (err) {
      console.log(err);
    } finally {
      const newServices = props.services.filter(
        (s) => s.idService !== props.service.idService
      );
      props.setServices(newServices);
    }
  };
  const editService = async () => {
    try {
      const response = await axios({
        method: "PATCH",
        url: "http://localhost:8080/services/" + props.service.idService,
        data: editedService,
        headers:{'Authorization': `Bearer ${authCtx.authData.token}`}

      });
      console.log(response);
    } catch (err) {
      console.log(err);
    } finally {
      handleShow();
      props.obtainServices();
    }
  };
  return (
    <>
      <tr>
        <td>{props.service.serviceName}</td>
        {/* <td>{(Math.round(props.service.service_price * 100) / 100).toFixed(2)+' GBP'}</td> */}
        <td>
          {(Math.round(props.service.servicePrice * 100) / 100).toFixed(2) +
            " GBP"}
        </td>
        <td>{props.service.approxTimeMin + " min"}</td>
        <td>
          {props.service.shortDescription.length > 100
            ? props.service.shortDescription.substring(0, 97) + "..."
            : props.service.shortDescription}
        </td>
        <td>
          <Dropdown className="m-0 p-0" align={"end"}>
            <Dropdown.Toggle variant="secondary">Action</Dropdown.Toggle>

            <Dropdown.Menu>
              <Dropdown.Item onClick={handleShow}>Edit</Dropdown.Item>
              <Dropdown.Item
                style={{ backgroundColor: "rgba(242, 97, 99,0.2)" }}
                onClick={deleteService}
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
        title={"Edit service - " + props.service.service_name}
        submitAction={editService}
        form={<EditServiceForm service={editedService} />}
      />
    </>
  );
};

export default Service;
