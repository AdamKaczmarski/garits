import Form from "react-bootstrap/Form";
const EditDescriptionRequired = (props) => {
  const descriptionHandler=(ev)=>{
    props.formData.descriptionRequired=ev.target.value;
  }
  return <Form>
    <Form.Group controlId="descriptionRequired">
      <Form.Label>Description Required</Form.Label>
      <Form.Control as="textarea" rows="3" onChange={descriptionHandler} defaultValue={props.descriptionRequired}/>
      </Form.Group>
  </Form>;
};

export default EditDescriptionRequired;
