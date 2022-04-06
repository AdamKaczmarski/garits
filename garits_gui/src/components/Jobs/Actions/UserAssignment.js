import Form from "react-bootstrap/Form";
import { useState, useCallback, useEffect } from "react";
import axios from "axios";
import Spinner from "react-bootstrap/Spinner";
const UserAssignment = (props) => {
  const [mechanics, setMechanics] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const obtainMechanics = async () => {
    try {
      const response = await axios({
        method: "GET",
        url: "http://localhost:8080/users/mechanics",
      });
      setMechanics(response.data);
    } catch (err) {
      console.log(err);
    } finally {
      setIsLoading(false);
    }
  };
  useEffect(() => {
    obtainMechanics();
  }, []);
  if (isLoading) return <Spinner animation="border" variant="primary" />;
  let mechanicsView;
  if (mechanics && mechanics.length > 0) {
    mechanicsView = mechanics.map((mechanic) => {
      if (props.user && mechanic.idUser !== props.user[0].idUser) {
        return (
          <option key={mechanic.idUser} value={mechanic.idUser}>
            {mechanic.firstName + " " + mechanic.lastName}
          </option>
        );
      } else {
        return (
            <option key={mechanic.idUser} value={mechanic.idUser}>
              {mechanic.firstName + " " + mechanic.lastName}
            </option>
          );
      }
    });
    if (props.user) {
      mechanicsView.unshift(
        <option key={props.user[0].idUser} value={props.user[0].idUser}>
          {props.user[0].firstName + " " + props.user[0].lastName}
        </option>
      );
    }
  }
  const mechanicHandler = (ev) => {
    props.formData.user[0].idUser = +ev.target.value;
  };
  const bayHandler = (ev) => {
    props.formData.bay = ev.target.value;
  };

  if (props.user) {
    let bay;
    if (props.bay && props.bay !== "MOT") {
      bay =
        props.bay.charAt(0).toUpperCase() + props.bay.slice(1).toLowerCase();
    } else bay = props.bay;
    props.formData.idUser = props.user[0].idUser;
    props.formData.bay = bay;
    return (
      <Form>
        <Form.Group controlId="assignee">
          <Form.Label>Assigned Mechanic</Form.Label>
          <Form.Select onChange={mechanicHandler}>{mechanicsView}</Form.Select>
        </Form.Group>
        <Form.Group controlId="bay">
          <Form.Label>Bay</Form.Label>
          <Form.Select onChange={bayHandler}>
            <option value={bay}>{bay}</option>
            <option value={props.bay === "MOT" ? "regular" : "MOT"}>
              {props.bay === "MOT" ? "Regular" : "MOT"}
            </option>
          </Form.Select>
        </Form.Group>
      </Form>
    );
  } else {
    console.log(props.formData)
    return (
      <Form>
        <Form.Group controlId="assignee">
          <Form.Label>Assign Mechanic</Form.Label>
          <Form.Select onChange={mechanicHandler}>{mechanicsView}</Form.Select>
        </Form.Group>
        <Form.Group controlId="bay">
          <Form.Label>Bay</Form.Label>
          <Form.Select onChange={bayHandler}>
            <option value="regular">Regular</option>
            <option value="MOT">MOT</option>
          </Form.Select>
        </Form.Group>
      </Form>
    );
  }
};

export default UserAssignment;
