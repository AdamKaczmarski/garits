import Form from 'react-bootstrap/Form'
const TYPES=['bank transfer','card','cash', 'check']
const SetPayDate = props => {
    const options = TYPES.map((type, index) => {
        return <option key={index}value={type}>{type.charAt(0).toUpperCase() +
            type.slice(1).toLowerCase()}</option>
    });
    const dateHandler=ev=>{
        props.finishPayment.paymentDate=ev.target.value;
    }
    const typeHandler =ev =>{
        props.finishPayment.cashOrCard=ev.target.value;
    }
    props.finishPayment.paymentDate=new Date().toISOString().substring(0, 10);
    props.finishPayment.cashOrCard=TYPES[0];
    return(
        <Form>
            <Form.Group controlId="setPayDate">
                <Form.Label>Payment Date</Form.Label>
                <Form.Control type="date" defaultValue={new Date().toISOString().substring(0, 10)} onChange={dateHandler}/>
            </Form.Group>
            <Form.Group controlId="payForm">
                <Form.Label>Payment Type</Form.Label>
                <Form.Select onChange={typeHandler}>{options}</Form.Select>
            </Form.Group>
        </Form>
    );
}

export default SetPayDate;