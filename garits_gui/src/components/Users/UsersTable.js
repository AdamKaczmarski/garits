import { useEffect, useState, useContext } from "react";
import AuthContext from "../../store/auth-context";

import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";

//import { USERS } from "../../dummy-data/users";
import User from "./User";
import AddUserModal from "./AddUserModal";
import axios from "axios";
import Spinner from "react-bootstrap/Spinner";
const UserTable = () => {
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);
  const [users, setUsers] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const authCtx  = useContext(AuthContext);
  console.log(authCtx)
  let userView;
  const obtainUsers = async () => {
    try {
      const response = await axios({
        method: "GET",
        url: "http://localhost:8080/users",
        headers:{'Authorization': `Bearer ${authCtx.authData.token}`}
      });
      setUsers(response.data);
    } catch (err) {
      console.log(err);
    } finally {
      setIsLoading(false);
    }
  };

  useEffect(() => {
    if (authCtx.authData.token)obtainUsers();
  }, [authCtx]);

  if (users && users.length > 0) {
    userView = users.map((user) => <User key={user.idUser} user={user} />);
  }
  if (isLoading){
    return <Spinner animation="border" variant="primary" />
  }
  return (
    <>
        <Table striped hover className="mt-3">
          <thead>
            <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Email</th>
              <th>Role</th>
              <th
                style={{ display: "flex" }}
                className="justify-content-between  "
              >
                Actions{" "}
                <Button variant="outline-primary" onClick={handleShow}>
                  +
                </Button>
              </th>
            </tr>
          </thead>
          <tbody>{userView}</tbody>
        </Table>
        <AddUserModal show={show} onClose={handleShow} obtainUsers={obtainUsers} />
    </>
  );
};

export default UserTable;
