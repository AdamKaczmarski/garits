import { useState } from "react";

import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";

import { USERS } from "../../dummy-data/users";
import User from "./User";
import AddUserModal from './AddUserModal';

const UserTable = () => {
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(!show);

  const users = USERS.map((user) => <User key={user.id} user={user} />);

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
              Action{" "}
              <Button variant="outline-primary" onClick={handleShow}>
                +
              </Button>
            </th>
          </tr>
        </thead>
        <tbody>{users}</tbody>
      </Table>
      <AddUserModal show={show} onClose={handleShow}/>
    </>
  );
};

export default UserTable;
