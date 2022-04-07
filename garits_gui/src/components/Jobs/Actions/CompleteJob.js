import { useState, useEffect, useCallback, useRef } from "react";
import axios from "axios";
import Form from "react-bootstrap/Form";
import Spinner from "react-bootstrap/Spinner";
import Button from "react-bootstrap/Button";
import SelectItem from "../../Inventory/Orders/SelectItem";
const CompleteJob = (props) => {
  const [isLoading, setIsLoading] = useState(true);
  const [items, setItems] = useState([]);
  let itemNames = useRef([]);
  console.log(props)
  const obtainItems = useCallback(async () => {
    try {
      const response = await axios({
        method: "GET",
        url: "http://localhost:8080/partNames",
      });
      console.log(response);
      if (response.status === 200) {
        itemNames.current = response.data.map((item) => (
          <option key={item.idPart} value={item.idPart}>
            {item.partName}
          </option>
        ));
        itemNames.current.unshift(
          <option key={0} value={0}>
            Add items...
          </option>
        );
        props.formData.parts.push({ idPart: 0, quantityUsed: 0 });
        setItems([
          <SelectItem
            itemNames={itemNames.current}
            key={Math.random()}
            item={props.formData.parts[props.formData.parts.length - 1]}
          />,
        ]);
      }
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
  const addItemSelect = () => {
    if (itemNames.current.length - 2 >= props.formData.parts.length) {
      props.formData.parts.push({ idPart: 0, quantityUsed: 0 });
      setItems([
        ...items,
        <SelectItem
          itemNames={itemNames.current}
          key={Math.random()}
          item={props.formData.parts[props.formData.parts.length - 1]}
        />,
      ]);
    } else {
      console.log("Cannot add any more items");
    }
  };

  const handleDescription = (ev) => {
    props.formData.descriptionDone = ev.target.value;
  };
  const timeHandler=ev=>{
    props.formData.actTimeMin=+ev.target.value;
  }
  return (
    <Form>
      <Form.Group controlId="Description done">
        <Form.Label>Description done</Form.Label>
        <Form.Control as="textarea" rows="3" onChange={handleDescription} defaultValue={props.descriptionRequired}/>
      </Form.Group>
      <Form.Group controlId="timeTaken">
        <Form.Label>Time taken</Form.Label>
        <Form.Control type="number" placeholder="Time taken" min={0} onChange={timeHandler}/>
      </Form.Group>
      <Form.Group controlId="Items">
        <Form.Label>Parts used</Form.Label> 
        {items}
      </Form.Group>
      <Button className="mt-3" onClick={addItemSelect}>
        Add item
      </Button>
    </Form>
  );
};

export default CompleteJob;
