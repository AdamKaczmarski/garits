import Form from 'react-bootstrap/Form'
const AddService = props =>{
    const serviceHandler=ev=>{
        props.service.idService=+ev.target.value;
    }
    return(
        <Form.Select className="mt-3" onChange={serviceHandler}>{props.servicesOptions}</Form.Select>
    );
}

export default AddService;