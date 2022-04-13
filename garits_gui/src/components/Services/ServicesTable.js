import { useEffect, useState, useContext , useCallback} from "react";
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";
//import { SERVICES } from "../../dummy-data/services";
import Service from "./Service";
import AddServiceForm from "./AddServiceForm";
import ServiceModal from "./ServiceModal";
import  Spinner  from "react-bootstrap/Spinner";
import axios from "axios";
import AuthContext from "../../store/auth-context";
const ServicesTable = () => {
  const [show, setShow] = useState(false);
  const [isLoading, setIsLoading] = useState(true);
  const [services, setServices] = useState([]);
  const handleShow = () => setShow(!show);
  const authCtx = useContext(AuthContext);
  let servicesView=[];
  let newService = {
    serviceName: "",
    servicePrice: 0,
    shortDescription: "",
    approxTimeMin: 0,
  };
  const obtainServices =useCallback( async () => {
    try {
      const response = await axios({
        method: "GET",
        url: "http://localhost:8080/services",
        headers:{'Authorization': `Bearer ${authCtx.authData.token}`}
      });
      setServices(response.data);
    } catch (err) {
    } finally {
      setIsLoading(false);
    }
  },[authCtx]);
  useEffect(() => {
    obtainServices();
  }, [obtainServices]);
  const addService = async () => {
    try {
      const response = await axios({
        method: "POST",
        url: "http://localhost:8080/services",
        data: newService,
        headers:{'Authorization': `Bearer ${authCtx.authData.token}`}

      });
      console.log(response);
    } catch (err) {
      console.log(err);
    } finally {
      /* const newServices = [...services];
      newServices.push(newService);
      newService = {
        serviceName: "",
        servicePrice: 0,
        shortDescription: "",
        approxTimeMin: 0,
      }; */
      handleShow();
      obtainServices();
    }
  };
  if (isLoading) {
    return <Spinner animation="border" variant="primary" />;
  }
  if (services && services.length>0) {
    servicesView = services.map((service) => (
      <Service key={service.idService} service={service} services={services} setServices={setServices} obtainServices={obtainServices}/>
    ));}
  
  return (
    <>
      <Table striped hover className="mt-3">
        <thead>
          <tr>
            <th>Name</th>
            <th>Price</th>
            <th>Approx. time</th>
            <th>Short description</th>
            <th
              style={{ display: "flex" }}
              className="justify-content-between  "
            >
              Actions
              <Button variant="outline-primary" onClick={handleShow}>
                +
              </Button>
            </th>
          </tr>
        </thead>
        <tbody>{servicesView}</tbody>
      </Table>
      <ServiceModal
        show={show}
        onClose={handleShow}
        title="Add service"
        submitAction={addService}
        form={<AddServiceForm service={newService}/>}
      />
    </>
  );
};

export default ServicesTable;
