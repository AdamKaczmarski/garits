import Form from "react-bootstrap/Form";
import { useEffect } from "react";
const AddService = (props) => {
     console.log(props)

   useEffect(() => {
    props.obtainPrice();
  }, []); 
  const serviceHandler = (ev) => {
      const selectedId=+ev.target.value;
    props.service.idService = +ev.target.value;
    props.id.idService=selectedId;
    props.obtainPrice();
  };
  return (
    <Form.Select className="mt-3" onChange={serviceHandler}>
      {props.servicesOptions}
    </Form.Select>
  );
};

export default AddService;
