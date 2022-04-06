const UserAssignment = props =>{
    if (props.user) {
        return(<p>{props.user.idUser}</p>);
    }
    else {
        return <p>Assign user</p>
    }
}

export default UserAssignment;