import { useEffect, useState, useCallback, useRef } from "react";
import Form from "react-bootstrap/Form";
import axios from "axios";
import Button from "react-bootstrap/Button";
import Spinner from "react-bootstrap/Spinner";
import AddService from "./AddService";
const AddJob = (props) => {
  const [isLoading, setIsLoading] = useState(true);
  const [customers, setCustomers] = useState([]);
  const [vehicles, setVehicles] = useState([]);
  const [services, setServices] = useState([]);
  const [servicesView, setServicesView] = useState([]);
  const selectedCustomer = useRef();
  const obtainCustomers = useCallback(async () => {
    try {
      const response = await axios({
        method: "GET",
        url: "http://localhost:8080/customers/short",
      });
      console.log(response);
      setCustomers(response.data);
      selectedCustomer.current = response.data[0];
    } catch (err) {
      console.log(err);
    }
  }, []);
  const obtainServices = useCallback(async () => {
    try {
      const response = await axios({
        method: "GET",
        url: "http://localhost:8080/services/short",
      });
      console.log(response);
      setServices(response.data);
    } catch (err) {
      console.log(err);
    }
  }, []);
  const obtainVehicles = useCallback(async () => {
    try {
      const response = await axios({
        method: "GET",
        url: `http://localhost:8080/vehicles/${selectedCustomer.current}/short`,
      });
      console.log(response);
      setVehicles(response.data);
    } catch (err) {
      console.log(err);
    }
  }, [selectedCustomer]);
  const obtainData = useCallback(async () => {
    try {
      await obtainCustomers();
      await obtainServices();
    } catch (err) {
      console.log(err);
    } finally {
      setIsLoading(false);
    }
  }, [obtainCustomers, obtainServices]);
  useEffect(() => {
    obtainData();
  }, [obtainData]);
  if (isLoading) {
    return <Spinner animation="border" variant="primary" />;
  }
  let customersOptions;
  if (customers) {
    customersOptions = customers.map((customer) => (
      <option key={customer.idCustomer} value={customer.idCustomer}>
        {customer.name}
      </option>
    ));
  }
  let vehiclesOptions;
  if (vehicles) {
    vehiclesOptions = vehicles.map((vehicle) => (
      <option key={vehicle.idVehicle} value={vehicle.idVehicle}>
        {vehicle.idRegNo}
      </option>
    ));
    vehiclesOptions.unshift(
      <option key={0} value={0}>
        Select vehicle..
      </option>
    );
  }
  let servicesOptions;
  if (services) {
    servicesOptions = services.map((service) => (
      <option key={service.idService} value={service.idService}>
        {service.serviceName}
      </option>
    ));
  }
  const addService = () => {
    if (servicesView.length < services.length) {
      props.selectedServices.push({ idService: 1 });
      setServicesView([
        ...servicesView,
        <AddService
          key={Math.random()}
          servicesOptions={servicesOptions}
          service={props.selectedServices[props.selectedServices.length - 1]}
        />,
      ]);
    } else {
      console.log("Cannot add any more items");
    }
  };
  const customerHandler = (ev) => {
    selectedCustomer.current = ev.target.value;
    obtainVehicles();
  };
  const vehicleHandler = (ev) => {
    props.newJob.vehicle.idVehicle = +ev.target.value;
  };
  const dateHandler = (ev) => {
    props.newJob.bookingDate = ev.target.value;
  };
  return (
    <Form>
      <Form.Group controlId="customer">
        <Form.Label>Customer</Form.Label>
        <Form.Select onChange={customerHandler}>{customersOptions}</Form.Select>
      </Form.Group>
      <Form.Group controlId="vehicle">
        <Form.Label>Vehicle</Form.Label>
        <Form.Select onChange={vehicleHandler}>{vehiclesOptions}</Form.Select>
      </Form.Group>
      <Form.Group controlId="service">
        <Form.Label>Services</Form.Label>
        {servicesView}
        {/* <Form.Select>{servicesOptions}</Form.Select> */}
      </Form.Group>
      <Button className="mt-3" onClick={addService}>
        Add service
      </Button>
      <Form.Group controlId="booking_date">
        <Form.Label onChange={dateHandler}>Booking date</Form.Label>
        <Form.Control
          type="date"
          defaultValue={new Date().toISOString().substring(0, 10)}
        />
      </Form.Group>
    </Form>
  );
};

export default AddJob;
