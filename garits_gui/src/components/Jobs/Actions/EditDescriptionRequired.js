import { useState, useEffect, useCallback } from "react";
import axios from "axios";
import Form from "react-bootstrap/Form";
import Spinner from "react-bootstrap/Spinner";
const EditDescriptionRequired = (props) => {
  const [isLoading, setIsLoading] = useState(true);
  const obtainItems = useCallback(async () => {
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
    obtainItems();
  }, [obtainItems]);
  if (isLoading) {
    return <Spinner variant="primary" animation="border" />;
  }
  return <Form></Form>;
};

export default EditDescriptionRequired;
