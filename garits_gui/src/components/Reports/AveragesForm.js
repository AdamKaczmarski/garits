import Form from "react-bootstrap/Form";
import { useState, useCallback, useEffect, useContext } from "react";
import axios from "axios";
import Button from "react-bootstrap/Button";
import Spinner from "react-bootstrap/Spinner";
import AuthContext from "../../store/auth-context";

const AveragesForm = (props) => {
  const [mechanics, setMechanics] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const authCtx = useContext(AuthContext);
  const [user, setUser] = useState({ idUser: 0 });
  const obtainMechanics = async () => {
    try {
      const response = await axios({
        method: "GET",
        url: "http://localhost:8080/users/mechanics",
        headers: { Authorization: `Bearer ${authCtx.authData.token}` },
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
    mechanicsView = mechanics.map((mechanic) => (
      <option key={mechanic.idUser} value={mechanic.idUser}>
        {mechanic.firstName + " " + mechanic.lastName}
      </option>
    ));
    mechanicsView.unshift(
      <option key={0} value={0}>
        {"All Mechanics"}
      </option>
    );
  }
  const mechanicHandler = (ev) => {
    setUser({ idUser: +ev.target.value });
  };
  return (
    <Form className="mt-5 mb-auto mx-auto" style={{ width: "32rem" }}>
      <Form.Group controlId="assignee">
        <Form.Label>Assigned Mechanic</Form.Label>
        <Form.Select onChange={mechanicHandler}>{mechanicsView}</Form.Select>
      </Form.Group>
      <Button onClick={()=>props.downloadReport(user.idUser)} className="mt-3">
        Download
      </Button>
    </Form>
  );
};

export default AveragesForm;
