import { useState, useEffect, useCallback } from "react";
import axios from "axios";
import Form from "react-bootstrap/Form";
import Spinner from "react-bootstrap/Spinner";
const EditServicesForJob = (props) => {
  const [isLoading, setIsLoading] = useState(true);
  const [services, setServices] = useState([]);
  const obtainServices = useCallback(async () => {
    try {
      const response = await axios({});
      console.log(response);
    } catch (err) {
      console.log(err);
    } finally {
      setIsLoading(false);
    }
  }, []);
  useEffect(() => {
    obtainServices();
  }, [obtainServices]);
  if (isLoading) {
    return <Spinner variant="primary" animation="border" />;
  }
  return <Form></Form>;
};

export default EditServicesForJob;
