import { useEffect, useState, Suspense } from "react";

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
  //const users = USERS.map((user) => <User key={user.id} user={user} />);
  let userView;
  useEffect(() => {
    obtainUsers();
  }, []);

  const obtainUsers = async () => {
    try {
      const response = await axios({
        method: "GET",
        url: "http://localhost:8080/users",
      });
      setUsers(response.data);
    } catch (err) {
      console.log(err);
    }
  };
  if (users && users.length > 0) {
    userView = users.map((user) => <User key={user.idUser} user={user} />);
  }

  return (
    <>
      <Suspense fallback={<Spinner animation="border" variant="primary" />}>
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
        <AddUserModal show={show} onClose={handleShow} />
      </Suspense>
    </>
  );
};

export default UserTable;
