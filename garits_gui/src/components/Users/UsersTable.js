import { useState } from "react";

import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";

import { USERS } from "../../dummy-data/users";
import User from "./User";

const UserTable = () => {
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

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
            <th className="justify-content-around">Action       <Button variant="primary" onClick={handleShow} className="pl-5">
        Add user
      </Button>
</th>
          </tr>
        </thead>
        <tbody>{users}</tbody>
      </Table>

    </>
  );
};

export default UserTable;
