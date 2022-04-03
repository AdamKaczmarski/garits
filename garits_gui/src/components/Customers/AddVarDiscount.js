import { useState, useEffect, useRef, useCallback } from "react";
import axios from "axios";
import Form from "react-bootstrap/Form";
const AddVarDiscount = (props) => {
  const [services, setServices] = useState([]);
  const serviceNameRef = useRef();

  let servicesOptions;
  /**
   * ADD SERVICES FILTERING SO THE ONES THAT CUSTOMER ALREADY HAS ARE NOT DISPLAYED IN THE OPTIONS
   */
  const obtainServices = useCallback(async () => {
    try {
      const response = await axios({
        method: "GET",
        url: "http://localhost:8080/services",
      });
      /*       const filteredServices = response.data
       */
      if (response.status === 200) {
        setServices(response.data);
        const newVarDisc = props.varDiscount;
        newVarDisc.serviceId = 1
      }
    } catch (err) {
      console.log(err);
    }
  }, [props.varDiscount]);

  if (services && services.length > 0) {
    /* const filteredServices = services.filter();
    console.log(filteredServices);
     */
    servicesOptions = services.map((service) => (

      <option key={service.idService} value={service.idService}>
        {service.serviceName}
      </option>
    ));
  }
  useEffect(() => {
    obtainServices();
  }, [obtainServices]);
  const serviceHandler = (ev) => {
    const newVarDisc = props.varDiscount;
    if (+ev.target.value === 0 ) newVarDisc.serviceId = 1;
    newVarDisc.serviceId = +ev.target.value;
  };
  const discountHandler = (ev) => {
    const newVarDisc = props.varDiscount;
    newVarDisc.discount = +ev.target.value;
  };
  return (
    <Form>
      <Form.Group>
        <Form.Label>Service</Form.Label>
        <Form.Select onChange={serviceHandler} ref={serviceNameRef}>
          {servicesOptions}
        </Form.Select>
      </Form.Group>

      <Form.Group>
        <Form.Label>Discount</Form.Label>
        <Form.Control
          type="number"
          min={0}
          onChange={discountHandler}
          defaultValue={0}
          max={100}
        />
      </Form.Group>
    </Form>
  );
};

export default AddVarDiscount;
